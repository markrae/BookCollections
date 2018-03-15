package org.launchcode.g7.bookcollections;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.launchcode.g7.bookcollections.models.Book;
import org.launchcode.g7.bookcollections.models.Shelf;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemListFragment.OnListFragmentInteractionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // TODO add new collection context behavior
                // if collections
                // open new collection dialog

                // TODO add new book context behavior
                // if inside collections,
                // open new book dialog

                // test bookReadWrite
                testBookReadWrite();
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

    @Override
    public void onListFragmentInteraction(Shelf item)
    {

    }

    /**
     * This method constructs a set of Book objects for testing. Note that the ISBNs and titles are
     * for actual books. This means that this test method can be used to verify book info retrieval.
     *
     * @return Shelf of Books
     */
    private Shelf buildTestShelf()
    {
        // create new shelf - name it test+TimeStamp
        Shelf shelf = new Shelf("test"+Long.toString(System.currentTimeMillis()));

        // create 3 test books
        Book ferretBook = new Book("9780702028274","Ferret Husbandry, Medicine and Surgery");
        Book incompedu =  new Book("9780345391377","An Incomplete Education");
        Book stardust =   new Book("9780380804559","Stardust");

        // add test books to shelf
        shelf.addBooks(ferretBook,incompedu,stardust);

        // return books in shelf
        return shelf;
    }

    /**
     * Testing method for verifying that the BookReadWrite behaviors are working properly in the
     * actual working context of our Activity.
     */
    private void testBookReadWrite()
    {
        BookReadWrite bookReadWrite = new BookReadWrite(getApplicationContext());

        // build a list of 3 shelves
        List<Shelf> testSavingList = new ArrayList<>();
        for(int i=1;i<=3;i++)
            testSavingList.add(buildTestShelf());

        // read any existing shelves
        List<Shelf> testReadingList = bookReadWrite.readShelves();

        // if shelves were already saved, add the lists. otherwise don't.
        if(testReadingList.get(1) != null)
            testSavingList.addAll(testReadingList);

        // save shelves
        bookReadWrite.saveShelves(testSavingList);

        // read shelves
        testReadingList = bookReadWrite.readShelves();

        if (testReadingList == null)
        {
            //TODO remove logs in final version
            Log.d("MainActTests", "Didn't read a file");
        }
        else
        {
            // output reading list's first Shelf name to log
            //TODO remove logs in final version
            Log.d("MainActTests",testReadingList.get(0).getName());
            Log.d("MainActTests",Integer.toString(testReadingList.size()));
        }
    }
}
