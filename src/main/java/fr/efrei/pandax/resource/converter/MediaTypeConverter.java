package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.MediaType;

import javax.ws.rs.ext.ParamConverter;

/**
 * Converts {@link fr.efrei.pandax.model.business.MediaType} to JSON (and vice-versa).
 */
public class MediaTypeConverter implements ParamConverter<MediaType> {
    private Gson cypher = new Gson();

    @Override
    public MediaType fromString(String s) {
        return cypher.fromJson(s, MediaType.class);
    }

    @Override
    public String toString(MediaType type) {
        return cypher.toJson(type);
    }
}
