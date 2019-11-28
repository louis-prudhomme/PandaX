package fr.efrei.pandax.model.core;

import fr.efrei.pandax.model.business.Possession;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class PossessionDAO extends AbstractDAO<Possession> {
    private TypedQuery<Possession> PossessionQuery;

    public PossessionDAO(){
        super(Possession.class);
    }

    public Possession readPossession(int idUser, int idMedia){
        openEntityManager();
        TypedQuery<Possession> query = em.createNamedQuery("Possession.findByUserAndMedia", Possession.class);
        query.setParameter("user", idUser);
        query.setParameter("media", idMedia);
        Possession x = query.getSingleResult();
        closeEntityManager();
        return x;
    }

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
