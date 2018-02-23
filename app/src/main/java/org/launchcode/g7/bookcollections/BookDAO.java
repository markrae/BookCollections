package org.launchcode.g7.bookcollections;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;
//hey
@Dao
public interface BookDAO
{
    @Query("SELECT * FROM Book")
    List<Book> getAll();

    @Query("SELECT * FROM Book WHERE uid IN (:userIds)")
    List<Book> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Book WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    Book findByName(String first, String last);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);
}