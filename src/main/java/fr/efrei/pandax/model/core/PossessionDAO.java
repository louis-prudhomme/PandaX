package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Possession;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * This class serves as an access to database-stored {@link Possession} entities.
 */
@Stateless
public class PossessionDAO extends AbstractDAO<Possession> {
    public PossessionDAO(){
        super(Possession.class);
    }

    /**
     * Reads the entity corresponding to the provided {@param id1, @param id2} in the database.
     * @return the corresponding entity
     */
    public Possession readPossession(int idUser, int idMedia){
        openEntityManager();
        TypedQuery<Possession> query = em.createNamedQuery("Possession.findByUserAndMedia", Possession.class);
        query.setParameter("user", idUser);
        query.setParameter("media", idMedia);
        Possession x = query.getSingleResult();
        closeEntityManager();
        return x;
    }


    /**
     * Deletes the {@link Possession} corresponding to the provided {@param id1, @param id2} in the database.
     * @param possession the {@link Possession} to delete.
     */
    public void deletePossession(Possession possession) {
        openEntityManager();
        em.getTransaction().begin();
        if (!em.contains(possession)) {
            possession = em.merge(possession);
        }
        em.remove(possession);
        em.flush();
        em.getTransaction().commit();
        closeEntityManager();
    }
}
