package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MediaDAO extends AbstractDAO<Media> {
    private TypedQuery<Media> mediaQuery;

    public MediaDAO(){
        super(Media.class);
    }

    public ArrayList<Media> getMediaByCity(String city){
        openEntityManager();
         mediaQuery= em.createNamedQuery("Media.findByCity", Media.class);
        mediaQuery.setParameter("city", city);
        ArrayList<Media> mediaList = (ArrayList<Media>) mediaQuery.getResultList();
        closeEntityManager();
        return mediaList;
    }

    public ArrayList<Media> getMediaByTitle(String title){
        openEntityManager();
        mediaQuery = em.createNamedQuery("Media.findByTitle", Media.class);
        mediaQuery.setParameter("title", title);
        ArrayList<Media> mediaList = (ArrayList<Media>) mediaQuery.getResultList();
        closeEntityManager();
        return mediaList;
    }

}

