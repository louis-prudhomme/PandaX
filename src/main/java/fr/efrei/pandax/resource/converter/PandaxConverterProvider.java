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
public class PandaxConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if(aClass == User.class) {
            return (ParamConverter<T>) new UserConverter();
        } else if (aClass == Media.class) {
            return (ParamConverter<T>) new MediaConverter();
        } else {
            return null;
        }
    }
}
