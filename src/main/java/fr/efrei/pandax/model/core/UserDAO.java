package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.User;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public User checkCredentials(String login, String password) {
        openEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class);
        query.setParameter("user", login);
        query.setParameter("password", password);
        User x = query.getSingleResult();
        closeEntityManager();
        return x;
    }
}
