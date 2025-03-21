package org.example.librarymanagement.dao;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.example.librarymanagement.model.Document;
import java.util.List;
@Named("documentDAO")
@ApplicationScoped
public class DocumentDAO extends GenericDAO<Document> {


    public DocumentDAO() {
        super(Document.class);
    }

    public List<Document> findByTitle(String title) {
        return em.createQuery("SELECT d FROM Document d WHERE d.title LIKE :title", Document.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }
}

