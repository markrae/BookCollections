package org.launchcode.g7.bookcollections.models;

import java.io.Serializable;

public final class Book implements Serializable {
    private String title;
    private final String isbn;

    public Book (String isbn){
        super();
        this.isbn = isbn;
    }

    public Book (String isbn, String title){
        this(isbn);
        this.title = title;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getIsbn() { return isbn; }


}
