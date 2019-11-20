package fr.efrei.pandax.model.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

//todo refactor open/close with lambda ?
public class AbstractDAO<T> {
    
    private Class<T> managedKlazz;

    private EntityManagerFactory emf;
    private EntityManager em;
    
    private ArrayList<T> all;

    public AbstractDAO(Class<T> klazz) {
        managedKlazz = klazz;
    }

    public T read(int id) {
        openEntityManager();
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getSimpleName() + ".findById", managedKlazz);
        query.setParameter("id", id);
        T x = query.getSingleResult();
        closeEntityManager();
        return x;
    }

    public void delete(T managedObject) {
        openEntityManager();
        em.getTransaction().begin();
        if (!em.contains(managedObject)) {
            managedObject = em.merge(managedObject);
        }
        em.remove(managedObject);
        em.getTransaction().commit();
        closeEntityManager();
    }

    public void modify(T managedObject) {
        openEntityManager();
        em.getTransaction().begin();
        em.merge(managedObject);
        em.getTransaction().commit();
        closeEntityManager();
    }

    public void create(T managedObject) {
        openEntityManager();
        em.getTransaction().begin();
        em.persist(managedObject);
        em.getTransaction().commit();
        closeEntityManager();
    }

    public ArrayList<T> getAll() {
        openEntityManager();
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getSimpleName() + ".findAll", managedKlazz);
        all = new ArrayList<>(query.getResultList());
        closeEntityManager();
        return all; 
    }

    public void openEntityManager() {
        emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        em.close();
        emf.close();
    }
}
