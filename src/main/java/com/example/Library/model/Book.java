package com.example.Library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author name is required")
    private String author;
    @NotBlank(message = "Genre is required")
    private String genre;
    @NotNull(message = "Year is required")
    @PositiveOrZero(message = "Year cannot be negative")
    private Integer publishedYear;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
}
