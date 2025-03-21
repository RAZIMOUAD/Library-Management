package org.example.librarymanagement.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Une seule table pour tous les types de documents
@DiscriminatorColumn(name = "document_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate dateCreated;


    public Document() {}
    public Document(String title, LocalDate dateCreated) {
        this.title = title;
        this.dateCreated = dateCreated;
    }
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDate getDateCreated() { return dateCreated; }
    public void setDateCreated(LocalDate dateCreated) { this.dateCreated = dateCreated; }



    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
