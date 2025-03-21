package org.example.librarymanagement.model;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("MAGAZINE")
public class Magazine extends Document {

    private String publisher;
    private String issueNumber;

    private LocalDate dateIssue;

    public Magazine() {}
    public Magazine(String title, LocalDate dateCreated, String publisher, String issueNumber, LocalDate dateIssue) {
        super(title, dateCreated);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
        this.dateIssue = dateIssue;
    }
    // Getters et Setters
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getIssueNumber() { return issueNumber; }
    public void setIssueNumber(String issueNumber) { this.issueNumber = issueNumber; }

    public LocalDate getDateIssue() { return dateIssue; }
    public void setDateIssue(LocalDate dateIssue) { this.dateIssue = dateIssue; }
}

