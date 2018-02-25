package org.launchcode.g7.bookcollections;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // connect the database variable to the database instance
        db = AppDatabase.getAppDatabase(this);

        // add testing data to database
        buildBasicBookData(5);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, getRandomBook().getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // TODO add new collection context behavior
                // if collections
                // open new collection dialog

                // TODO add new book context behavior
                // if inside collections,
                // open new book dialog

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void buildBasicBookData(int count)
    {
        Book b = new Book();
        for(int i=0;i<count;i++)
        {
            b.setUid(i);
            b.setIsbn("Tale of "+Integer.toString(i)+" cities");
            b.setTitle(Integer.toString(i));
            db.bookDao().insertAll(b);
        }
    }
    private Book getRandomBook()
    {
        // get a random int from 0 to 5
        int i = new Random().nextInt(5);
        // return a Book that responds to that "isbn"
        return db.bookDao().findByIsbn(Integer.toString(i));
    }
}
