package fr.efrei.pandax.model.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

//todo refactor open/close with lambda ?

/**
 * Represents an general Database Access Object.
 * Can generically read, delete, update, create and get all records of a generic type.
 * @param <T> Generic type that will be queried in database. Should be a fr.efrei.pandax.model.business class.
 */
public abstract class AbstractDAO<T> {
    /**
     * Target class managed by the DAO.
     */
    private Class<T> managedKlazz;

    /**
     * FUK U TOMCAT
     */
    private EntityManagerFactory emf;
    /**
     * DITTO
     */
    protected EntityManager em;

    /**
     * Standard constructor.
     * @param klazz managed entity.
     */
    public AbstractDAO(Class<T> klazz) {
        managedKlazz = klazz;
    }

    /**
     * Reads the entity corresponding to the provided {@param id} in the database.
     * @param id of the desired entity in database.
     * @return a {@param T}-typed class matching database records for the provided {@param id}.
     */
    public T read(int id) {
        openEntityManager();
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getSimpleName() + ".findById", managedKlazz);
        query.setParameter("id", id);
        T x = query.getSingleResult();
        closeEntityManager();
        return x;
    }

    /**
     * Deletes the specified entity from the database.
     * @param managedObject entity to delete.
     */
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

    /**
     * Modifies the specified entity from the database.
     * @param managedObject entity to modify.
     */
    public T modify(T managedObject) {
        openEntityManager();
        em.getTransaction().begin();
        em.merge(managedObject);
        em.getTransaction().commit();
        closeEntityManager();
        return managedObject;
    }

    /**
     * Creates the specified entity from the database.
     * @param managedObject entity to create.
     */
    public T create(T managedObject) {
        openEntityManager();
        em.getTransaction().begin();
        em.persist(managedObject);
        em.flush();
        em.getTransaction().commit();
        closeEntityManager();
        return managedObject;
    }

    /**
     * Obtains all the entities in database.
     * @return all the entities.
     */
    public ArrayList<T> getAll() {
        openEntityManager();
        TypedQuery<T> query = em.createNamedQuery(managedKlazz.getSimpleName() + ".findAll", managedKlazz);
        ArrayList<T> all = new ArrayList<>(query.getResultList());
        closeEntityManager();
        return all;
    }

    protected void openEntityManager() {
        emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        em = emf.createEntityManager();
    }

    protected void closeEntityManager() {
        em.close();
        emf.close();
    }
}
