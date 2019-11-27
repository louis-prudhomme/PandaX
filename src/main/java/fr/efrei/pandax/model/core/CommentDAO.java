package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Comment;
import fr.efrei.pandax.model.business.CommentPK;
import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CommentDAO extends AbstractDAO<Comment> {
    public CommentDAO(){
        super(Comment.class);
    }

    public List<Comment> getByUser(int idUser) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByUser", Comment.class);
        query.setParameter("user", idUser);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }

    public List<Comment> getByMedia(int idMedia) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMedia", Comment.class);
        query.setParameter("media", idMedia);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }

    public List<Comment> getByMediaAndUser(int idMedia, int idUser) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMediaAndUser", Comment.class);
        query.setParameter("media", idMedia);
        query.setParameter("user", idUser);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }

    public Comment getByPk(int idComment, int idMedia, int idUser) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByPk", Comment.class);
        query.setParameter("id", idComment);
        query.setParameter("media", idMedia);
        query.setParameter("user", idUser);
        Comment c = query.getSingleResult();
        closeEntityManager();
        return c;
    }
}
