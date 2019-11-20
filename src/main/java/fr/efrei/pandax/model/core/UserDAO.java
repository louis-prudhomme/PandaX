package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Stateless
public class UserDAO extends AbstractDAO<User> {
    public UserDAO() {
        super(User.class);
    }

    public User checkCredentials(String login, String password) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createNamedQuery("User.findByCredentials", User.class);
        query.setParameter("user", login);
        query.setParameter("password", password);
        User x = query.getSingleResult();
        em.close();
        emf.close();
        return x;
    }
}
