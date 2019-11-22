package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.CommentPK;
import javax.ejb.Stateless;

@Stateless
public class CommentsPKDAO extends AbstractDAO<CommentPK> {
    public CommentsPKDAO(){
        super(CommentPK.class);
    }
}
