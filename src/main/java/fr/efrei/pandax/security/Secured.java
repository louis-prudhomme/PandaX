package fr.efrei.pandax.security;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom-made annotation to mark the REST API elements to provide them with security (class or methods).
 * It is used to specify the authorized {@link Role} in an element and bridges the gap between the element and the actual {@link AuthenticationFilter}
 * Method-defined {@link Role} override class-defined ones.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {
    /**
     * {@link java.lang.reflect.Array} of authorized {@link Role} in the annotated element.
     * @return authorized {@link Role}.
     */
    Role[] value() default {};
}
