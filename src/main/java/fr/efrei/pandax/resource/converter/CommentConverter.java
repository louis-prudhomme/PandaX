package fr.efrei.pandax.resource.converter;

import com.google.gson.Gson;
import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.business.Media;

import javax.ws.rs.ext.ParamConverter;

/**
 * Converts {@link Comment} to JSON (and vice-versa).
 */
public class CommentConverter implements ParamConverter<Comment> {
    private Gson cypher = new Gson();

    @Override
    public Comment fromString(String s) {
        return cypher.fromJson(s, Comment.class);
    }

    @Override
    public String toString(Comment c) {
        return cypher.toJson(c);
    }
}
