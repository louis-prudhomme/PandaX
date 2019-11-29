package fr.efrei.pandax.utils;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This class handles all the {@link PersistenceException} arising in our application.
 * It returns appropriate HTTP codes for each error.
 * Registered in web.xml
 */
@Provider
public class PersistenceExceptionHandler implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(PersistenceException ex) {
        if(ex.getCause().getClass() == ConstraintViolationException.class) {
            return Response.status(Response.Status.CONFLICT).build();
        } else if(ex.getCause().getClass() == NoResultException.class) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
