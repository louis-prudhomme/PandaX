package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.MediaType;
import fr.efrei.pandax.model.business.Publisher;

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

    /**
     * Retrives every {@link Media} with specified {@link MediaType}
     * @param idMedia desired {@link MediaType}
     * @return an {@link List} of all matching {@link Media}
     */
    public List<Media> getAllForType(int idMedia) {
        openEntityManager();
        TypedQuery<Media> query = em.createNamedQuery("Media.findByMediaType", Media.class);
        query.setParameter("mediaType", idMedia);
        List<Media> media = query.getResultList();
        closeEntityManager();
        return media;
    }

    /**
     * Retrives every {@link Media} with specified {@link Publisher}
     * @param idPublisher desired {@link Publisher}
     * @return an {@link List} of all matching {@link Media}
     */
    public List<Media> getAllForPublisher(int idPublisher) {
        openEntityManager();
        TypedQuery<Media> query = em.createNamedQuery("Media.findByPublisher", Media.class);
        query.setParameter("publisher", idPublisher);
        List<Media> mediaList = query.getResultList();
        closeEntityManager();
        return mediaList;
    }
}

