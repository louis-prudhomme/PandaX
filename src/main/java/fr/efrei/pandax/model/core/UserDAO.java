package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.User;
import org.hibernate.query.Query;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO extends AbstractDAO<User> {
    private ArrayList<Media> possessionMediaList;
    public UserDAO() {
        super(User.class);
        possessionMediaList = new ArrayList<Media>();
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
        Query<Integer> query = (Query<Integer>) em.createQuery("SELECT p.possessionPK.media FROM Possession p WHERE p.possessionPK.user =: user");
        query.setParameter("user",idUser);

        for(int nb : query.getResultList()){
            Query<Media> queryMedia = (Query<Media>) em.createQuery("SELECT m FROM Media m WHERE m.id =: id");
            queryMedia.setParameter("id", nb);
            possessionMediaList.add(queryMedia.getSingleResult());
        }
        closeEntityManager();
        return possessionMediaList;
    }
}
