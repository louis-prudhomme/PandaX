package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Comment;
import javax.ejb.Stateless;

@Stateless
public class CommentsDAO extends AbstractDAO<Comment> {
    public CommentsDAO(){
        super(Comment.class);
    }   
}
