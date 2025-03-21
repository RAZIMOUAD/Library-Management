package org.example.librarymanagement.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import  org.example.librarymanagement.model.User;
import  org.example.librarymanagement.model.Document;
import  org.example.librarymanagement.config.PersistenceManager;
import java.util.List;

@ApplicationScoped
@Named("borrowDAO")
public class BorrowDAO {

    private final EntityManager em = PersistenceManager.getEntityManager();

    private final UserDAO userDAO;
    private final DocumentDAO documentDAO;

    public BorrowDAO() {
        this.userDAO = new UserDAO();
        this.documentDAO = new DocumentDAO();
    }
    public void borrowDocument(Long userId, Long documentId) {
        User user = userDAO.findById(userId);
        Document document = documentDAO.findById(documentId);

        if (user != null && document != null) {
            try {
                em.getTransaction().begin();
                user.getBorrowedDocuments().add(document);
                em.merge(user);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw new RuntimeException("Erreur lors de l'emprunt du document : " + e.getMessage());
            }
        }
    }

    public List<Document> getBorrowedDocuments(Long userId) {
        User user = userDAO.findById(userId);
        return user != null ? user.getBorrowedDocuments().stream().toList() : List.of();  // ✅ Retourne une liste vide si `user` est `null`
    }

    public void returnDocument(Long userId, Long documentId) {
        User user = userDAO.findById(userId);
        Document document = documentDAO.findById(documentId);

        if (user != null && document != null) {
            try {
                em.getTransaction().begin();
                user.getBorrowedDocuments().remove(document);
                em.merge(user);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw new RuntimeException("Erreur lors du retour du document : " + e.getMessage());
            }
        }
    }
}
