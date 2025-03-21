package org.example.librarymanagement.dao;



import org.example.librarymanagement.config.PersistenceManager;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericDAO<T> {
    private final Class<T> entityClass;
    protected EntityManager em = PersistenceManager.getEntityManager();

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public T findById(Long id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        T entity = findById(id);
        if (entity != null) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        }
    }
}

