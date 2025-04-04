package org.example.librarymanagement.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
