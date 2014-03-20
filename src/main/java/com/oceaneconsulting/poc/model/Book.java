package com.oceaneconsulting.poc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ubuntu on 3/20/14.
 */
@Entity
@Table(name = "BOOKS")
@Audited
public class Book implements Serializable{


    private static final long serialVersionUID = 19981017L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Long id;

    private String title;
    private String authorName;
    private String isbn;

    public Book() {
    }

    public Book(String title, String authorName, String isbn) {
        this.title = title;
        this.authorName = authorName;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
