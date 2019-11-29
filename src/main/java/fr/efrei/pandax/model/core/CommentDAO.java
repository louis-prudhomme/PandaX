package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Comment;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class serves as an access to database-stored {@link Comment} entities.
 */
@Stateless
public class CommentDAO extends AbstractDAO<Comment> {
    public CommentDAO(){
        super(Comment.class);
    }

    /**
     * Gets every {@link Comment} of a specific {@link fr.efrei.pandax.model.business.User}, represented by its {@link fr.efrei.pandax.model.business.User#id}.
     * @param idUser should be the {@link fr.efrei.pandax.model.business.User#id}
     * @return a {@link List} of matching {@link Comment}
     */
    public List<Comment> getByUser(int idUser) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByUser", Comment.class);
        query.setParameter("user", idUser);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }

    /**
     * Gets every {@link Comment} of a specific {@link fr.efrei.pandax.model.business.Media}, represented by its {@link fr.efrei.pandax.model.business.Media#id}.
     * @param idMedia should be the {@link fr.efrei.pandax.model.business.Media#id}
     * @return a {@link List} of matching {@link Comment}
     */
    public List<Comment> getByMedia(int idMedia) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMedia", Comment.class);
        query.setParameter("media", idMedia);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }


    /**
     * Gets every {@link Comment} for specifics {@link fr.efrei.pandax.model.business.Media} and {@link fr.efrei.pandax.model.business.User}
     * They are respectively represented by their {@link fr.efrei.pandax.model.business.Media#id} and {@link fr.efrei.pandax.model.business.User#id}.
     * @param idMedia should be the {@link fr.efrei.pandax.model.business.Media#id}
     * @param idUser should be the {@link fr.efrei.pandax.model.business.User#id}
     * @return a {@link List} of matching {@link Comment}
     */
    public List<Comment> getByMediaAndUser(int idMedia, int idUser) {
        openEntityManager();
        TypedQuery<Comment> query = em.createNamedQuery("Comment.findByMediaAndUser", Comment.class);
        query.setParameter("media", idMedia);
        query.setParameter("user", idUser);
        List<Comment> comments = query.getResultList();
        closeEntityManager();
        return comments;
    }

    /**
     * Gets a specific comment using every identifier involved with it.
     * @param idComment should be the {@link fr.efrei.pandax.model.business.Comment#id}
     * @param idMedia should be the {@link fr.efrei.pandax.model.business.Media#id}
     * @param idUser should be the {@link fr.efrei.pandax.model.business.User#id}
     * @return a specific {@link Comment}
     */
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
