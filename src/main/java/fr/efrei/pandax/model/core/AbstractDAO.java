package fr.efrei.pandax.model.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class AbstractDAO<T> {
    
    private Class<T> managedKlazz;

    private EntityManagerFactory emf;
    private EntityManager em;
    
    private ArrayList<T> all;

    public AbstractDAO(Class<T> klazz) {
        managedKlazz = klazz;
    }

    public T read(int id) {
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getName() + ".findById", managedKlazz);    
        emf= Persistence.createEntityManagerFactory("my_persistence_unit") ;
        em= emf.createEntityManager();
        query.setParameter("id",id); 
        T x = query.getSingleResult();
        em.close();
        emf.close();
        return x; 
    }

    public void delete(T managedObject) {
        emf= Persistence.createEntityManagerFactory("my_persistence_unit") ;
        em= emf.createEntityManager();
        em.getTransaction().begin();
        if (!em.contains(managedObject)) {
            managedObject = em.merge(managedObject);
        }
        em.remove(managedObject);
        em.getTransaction().commit() ;
        em.close();
        emf.close();
    }

    public void modify(T managedObject) {
        emf= Persistence.createEntityManagerFactory("my_persistence_unit") ;
        em= emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(managedObject);
        em.getTransaction().commit() ;
        em.close();
        emf.close();
    }

    public void create(T managedObject) {  
        emf= Persistence.createEntityManagerFactory("my_persistence_unit") ;
        em= emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(managedObject);
        em.getTransaction().commit() ;
        em.close();
        emf.close();
    }

    public ArrayList<T> getAll() {
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getName() + ".findAll", managedKlazz);      
        emf= Persistence.createEntityManagerFactory("my_persistence_unit") ;
        em= emf.createEntityManager();
        all = new ArrayList<>(query.getResultList());
        em.close();
        emf.close();
        return all; 
    }
}
