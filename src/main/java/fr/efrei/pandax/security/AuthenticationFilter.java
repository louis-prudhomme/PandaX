package fr.efrei.pandax.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Acts as a security filter and checks every incoming request before actual processing.
 * Checks JWTs and {@link Role}s
 */
public class AuthenticationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    /**
     * Security handler which verifies both the JWT validity (if provided) and the {@link Role} accreditation.
     * If all goes according to the plan, this method should fire and do nothing.
     * @param context context of the request.
     */
    @Override
    public void filter(ContainerRequestContext context) {
        String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        List<Role> methodAuthorizedRoles = extractRolesFromContext(resourceInfo.getResourceMethod());
        List<Role> classAuthorizedRoles = extractRolesFromContext(resourceInfo.getResourceClass());

        Jwt jwt = checkJwt(authorizationHeader);
        // if the jwt is valid
        if(jwt == null) {
            //todo return another error code
            context.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            return;
        }

        // if roles are defined for the context method
        // else we check for the context class
        // it means that method security overrides classes security
        if (!methodAuthorizedRoles.isEmpty()) {
            // if the role of the request is included in the authorized roles
            if(!checkPermission(methodAuthorizedRoles, extractRoleFromJwt(jwt))) {
                context.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        } else {
            // if the role of the request is included in the authorized roles
            if(!checkPermission(classAuthorizedRoles, extractRoleFromJwt(jwt))) {
                context.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }

    /**
     * Checks if the provided {@link String}-represented JWT is valid.
     * @param encodedJwt {@link String}-represented JWT.
     * @return if the provided JWT is valid, a {@link Jwt} object ; otherwise, null.
     */
    private Jwt checkJwt(String encodedJwt) {
        try
        {
            return Jwts.parser().setSigningKey(new TokenHelper().generateSecretKey()).parse(encodedJwt);
        } catch (JwtException e) {
            return null;
        }
    }

    /**
     * Checks if a {@link Role} is amongst a {@link List} of {@link Role}.
     * @param authorizedRoles {@link List} of authorized {@link Role}.
     * @param incomingRole {@link Role} to check.
     * @return true if the {@link Role} belongs, false otherwise.
     */
    private boolean checkPermission(List<Role> authorizedRoles, Role incomingRole) {
        return authorizedRoles.contains(incomingRole);
    }

    /**
     * Obtains the claimed {@link Role} in a {@link Jwt}.
     * @param jwt {@link Jwt} to extract the {@link Role} from.
     * @return a {@link Role}
     */
    private Role extractRoleFromJwt(Jwt jwt) {
        return Role.fromString(((Claims)jwt.getBody())
                .get("rol", String.class));
    }

    /**
     * Extracts the authorized {@link Role} from the context of the called method / class.
     * @param annotatedElement called class or method.
     * @return {@link List} of authorized {@link Role} in the {@param annotatedElement}.
     */
    private List<Role> extractRolesFromContext(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<>();
        } else {
            //the secured annotation contains the authorized roles
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }
}
