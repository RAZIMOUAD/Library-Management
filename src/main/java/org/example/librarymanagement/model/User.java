package org.example.librarymanagement.model;



import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String mail;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_document",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private Set<Document> borrowedDocuments = new HashSet<>();

    public User() {}
    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public Set<Document> getBorrowedDocuments() { return borrowedDocuments; }
    public void setBorrowedDocuments(Set<Document> borrowedDocuments) { this.borrowedDocuments = borrowedDocuments; }
}

