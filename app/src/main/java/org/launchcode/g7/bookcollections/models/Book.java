package org.launchcode.g7.bookcollections.models;

public class Book {
    private String title;
    private final String isbn;

    public Book (String isbn){
        this.isbn = isbn;
    }

    public Book (String isbn, String title){
        this.isbn = isbn;
        this.title = title;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIsbn() { return isbn; }
}
