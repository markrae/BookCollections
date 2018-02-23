package org.launchcode.g7.bookcollections;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Mark Rae on 2/22/2018.
 */

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract BookDAO userDao();
}
