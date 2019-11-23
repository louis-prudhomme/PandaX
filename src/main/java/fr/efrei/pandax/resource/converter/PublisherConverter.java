package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.Publisher;

import javax.ws.rs.ext.ParamConverter;

/**
 * Converts {@link Publisher} to JSON (and vice-versa).
 */
public class PublisherConverter implements ParamConverter<Publisher> {
    private Gson cypher = new Gson();

    @Override
    public Publisher fromString(String s) {
        return cypher.fromJson(s, Publisher.class);
    }

    @Override
    public String toString(Publisher p) {
        return cypher.toJson(p);
    }
}
