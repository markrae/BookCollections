package org.launchcode.g7.bookcollections;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Book
{
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "isbn")
    private String isbn;

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
// Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
