package org.launchcode.g7.bookcollections.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Collection is meant to hold a List of Book objects.
 */

class Collection implements Serializable
{
    private ArrayList<Book> collection;
    private String name;

    Collection(String name)
    {
        this.name=name;
    }
    void addBooks(Book... books)
    {
        for (Book individualBook: books)
        {
            collection.add(individualBook);
        }
    }
}