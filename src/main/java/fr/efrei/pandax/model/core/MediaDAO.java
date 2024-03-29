package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Media;
import fr.efrei.pandax.model.business.MediaType;
import fr.efrei.pandax.model.business.Publisher;
import org.hibernate.criterion.MatchMode;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class MediaDAO extends AbstractDAO<Media> {
    public MediaDAO(){
        super(Media.class);
    }

    /**
     * Returns a {@link List} of all the {@link Media}.
     * Allows parameter matching for {@link Media#title} and {@link Media#city}
     * @param title complete or approximate {@link Media#title}
     * @param city complete or approximate {@link Media#city}
     * @param descript complete or approximate {@link Media#descript}
     * @return {@link List} of all the matching {@link Media}
     */
    public List<Media> getAll(String title, String city, String descript) {
        openEntityManager();
        TypedQuery<Media> mediaQuery = em.createNamedQuery("Media.findAll", Media.class);
        mediaQuery.setParameter("title",
                MatchMode.ANYWHERE.toMatchString(title));
        mediaQuery.setParameter("city",
                MatchMode.ANYWHERE.toMatchString(city));
        mediaQuery.setParameter("descript",
                MatchMode.ANYWHERE.toMatchString(descript));
        List<Media> mediaList = mediaQuery.getResultList();
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

