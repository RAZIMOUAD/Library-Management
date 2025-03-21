package org.example.librarymanagement.model;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("BOOK")
public class Book extends Document {

    private String author;
    private String isbn;


    private LocalDate datePublication;
    public Book() {}
    public Book(String title, LocalDate dateCreated, String author, String isbn, LocalDate datePublication) {
        super(title, dateCreated);
        this.author = author;
        this.isbn = isbn;
        this.datePublication = datePublication;
    }
    // Getters et Setters
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDate datePublication) { this.datePublication = datePublication; }
}
