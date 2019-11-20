package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.Media;

import javax.ws.rs.ext.ParamConverter;

public class MediaConverter implements ParamConverter<Media> {
    private Gson cypher = new Gson();

    @Override
    public Media fromString(String s) {
        return cypher.fromJson(s, Media.class);
    }

    @Override
    public String toString(Media media) {
        return cypher.toJson(media);
    }
}
