package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.CommentsPK;
import javax.ejb.Stateless;

@Stateless
public class CommentsPKDAO extends AbstractDAO<CommentsPK> {
    public CommentsPKDAO(){
        super(CommentsPK.class);
    }
}
