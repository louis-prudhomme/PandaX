package fr.efrei.pandax.resource.converter;

import fr.efrei.pandax.model.business.*;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

//todo replace this class with POJOMappingFeature
/* in web xml
<init-param>
    <param-name>com.sun.jersey.api.json.POJOMappingFeature</param-name>
    <param-value>true</param-value>
</init-param>
 */

/**
 * The {@link ParamConverterProvider} which links the application to its entities converters.
 */
public class PandaxConverterProvider implements ParamConverterProvider {
    /**
     * Links the program to the corresponding PandaX entity {@link ParamConverter}.
     * @param aClass Raw type of the parameter provided, should be {@link String}.
     * @param type To which the conversion should be executed.
     * @param annotations Annotations of the caller context.
     * @param <T> Genericity provided by parent method.
     * @return
     */
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(type == User.class) {
            return (ParamConverter<T>) new UserConverter();
        } else if (type == Media.class) {
            return (ParamConverter<T>) new MediaConverter();
        } else if(type == MediaType.class) {
            return (ParamConverter<T>) new MediaTypeConverter();
        } else if(type == Publisher.class) {
            return (ParamConverter<T>) new PublisherConverter();
        } else if(type == Comment.class) {
            return (ParamConverter<T>) new CommentConverter();
        } else if(type == Publisher.class) {
            return (ParamConverter<T>) new PublisherConverter();
        } else {
            return null;
        }
    }
}
