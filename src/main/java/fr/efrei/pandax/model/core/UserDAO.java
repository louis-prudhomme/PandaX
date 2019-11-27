package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public User checkCredentials(String login, String password) {
        openEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.checkCred", User.class);
        query.setParameter("pseudo", login);
        query.setParameter("pwd", password);
        User x = query.getSingleResult();
        closeEntityManager();
        return x;
    }

    public List<Media> getAllPossessions(int idUser){
        openEntityManager();
        TypedQuery<Media> mediaQuery = em.createNamedQuery("Media.findByUser", Media.class);
        mediaQuery.setParameter("user", idUser);
        List<Media> mediaList = mediaQuery.getResultList();
        closeEntityManager();
        return mediaList;
    }
}
