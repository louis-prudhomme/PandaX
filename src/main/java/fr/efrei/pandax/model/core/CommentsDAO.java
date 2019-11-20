package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Comments;
import javax.ejb.Stateless;

@Stateless
public class CommentsDAO extends AbstractDAO<Comments> {
    public CommentsDAO(){
        super(Comments.class);
    }   
}
