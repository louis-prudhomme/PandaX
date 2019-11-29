package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * This class serves as an access to database-stored {@link User} entities.
 */
@Stateless
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    /**
     * Checks that the entered credentials correspond to an existing {@link User}
     * @param login the {@link User#pseudo}
     * @param password the {@link User#pwd}
     * @return the corresponding {@link User}
     */
    public User checkCredentials(String login, String password) {
        openEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.checkCred", User.class);
        query.setParameter("pseudo", login);
        query.setParameter("pwd", password);
        User x = query.getSingleResult();
        closeEntityManager();
        return x;
    }

    /**
     * Obtains all the {@link fr.efrei.pandax.model.business.Possession} of an specific {@link User}.
     * @param idUser {@link User#id} of the {@link User}
     * @return all correlated {@link Media}
     */
    public List<Media> getAllPossessions(int idUser) {
        openEntityManager();
        TypedQuery<Media> mediaQuery = em.createNamedQuery("Media.findByUser", Media.class);
        mediaQuery.setParameter("user", idUser);
        List<Media> mediaList = mediaQuery.getResultList();
        closeEntityManager();
        return mediaList;
    }


}
