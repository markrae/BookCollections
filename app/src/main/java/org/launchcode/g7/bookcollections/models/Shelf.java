package org.launchcode.g7.bookcollections.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Shelf is meant to hold a List of Book objects.
 */

public class Shelf implements Serializable
{
    private List<Book> shelf;
    private String name;

    /**
     * Creates a new container for books.
     * @param name every Shelf must have a name
     */
    public Shelf(String name)
    {
        this.name = name;
        this.shelf = new ArrayList<Book>();
    }

    /**
     * Adds one or more Book objects to the container.
     * @param books Book objects separated by commas
     */
    public void addBooks(Book... books)
    {
        for (Book individualBook: books)
        {
            shelf.add(individualBook);
        }
    }
    public String getName()
    {
        return this.name;
    }
}