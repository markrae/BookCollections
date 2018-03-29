package org.launchcode.g7.bookcollections;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.launchcode.g7.bookcollections.models.Book;
import org.launchcode.g7.bookcollections.models.Shelf;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ItemListFragment.OnListFragmentInteractionListener,
        NewShelfFragment.OnBuildShelfClickListener,
        NewBookFragment.OnAddBookClickListener
{
    private RecyclerView ARecyclerView;
    private int selectedShelfIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ARecyclerView = findViewById(R.id.fragment);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                // TODO add new collection context behavior
                // if collections
                // open new collection dialog
                if (inShelfView())
                {
                    NewShelfFragment shelfFragment = new NewShelfFragment();
                    shelfFragment.show(getSupportFragmentManager(), "newshelfdialog");
                }
                // TODO add new book context behavior
                // if inside collections,
                // open new book dialog
                else
                {
                    NewBookFragment bookFragment = new NewBookFragment();
                    bookFragment.show(getSupportFragmentManager(), "newbookdialog");
                }

                // test bookReadWrite
                //testBookReadWrite();
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
    public void onListFragmentInteraction(Object item,int position)
    {
        // get a pointer to the recyclerView


        // if viewing the Shelf List
        if (inShelfView())
        {
            // then item is a Shelf, so cast it as such.
            Shelf selectedShelf = (Shelf) item;
            // store the item's position
            selectedShelfIndex = position;
            // swap adapter to BookAdapter
            ARecyclerView.setAdapter(
                    new BookRecyclerViewAdapter(
                            selectedShelf.getAllBooks(),
                            this));
        }
        // if viewing a Book list (i.e. inside a Shelf)
        // show book info?
    }

    // Called when user enters a shelf name and clicks Build Shelf
    @Override
    public void onBuildShelfClick(Shelf newShelf)
    {
        // add the shelf to the file
        addShelf(newShelf);
    }

    /**
     * Adds one Shelf to the file.
     * @param shelf new shelf to be appended to the file.
     */
    private void addShelf(Shelf shelf)
    {
        // get an instance of BookReadWrite
        BookReadWrite bookReadWrite = new BookReadWrite(getApplicationContext());

        // read any existing shelves
        List<Shelf> shelvesInFile = bookReadWrite.readShelves();

        // add new shelf to List of existing shelves
        shelvesInFile.add(shelf);

        // save shelves
        bookReadWrite.saveShelves(shelvesInFile);
    }

    @Override
    public void onAddBookClick(Book newBook)
    {
        addBook(newBook);
    }

    /**
     * Adds a book the the selectedShelf.
     * @param book a Book object to be added to currently viewed Shelf
     */
    private void addBook(Book book)
    {
        // selectedShelf is a copy, I think, i.e. it isn't the saved version of the shelf.

        // get an instance of BookReadWrite
        BookReadWrite bookReadWrite = new BookReadWrite(getApplicationContext());

        // read any existing shelves
        List<Shelf> shelvesInFile = bookReadWrite.readShelves();

        // get a pointer to the shelf that's in the file
        Shelf shelfInFile = shelvesInFile.get(selectedShelfIndex);

        // add the book to the shelf
        shelfInFile.addBooks(book);

        // save shelves
        bookReadWrite.saveShelves(shelvesInFile);

        // TODO Scrap this and make a BookReadWrite method for saving individual books to a shelf
    }

    /**
     * Use when you need to test if MainActivity is currently in Shelf or Book viewing mode. While
     * methods inside the MainActivity class don't really need this, it may be useful in case other
     * classes need to test what mode MainActivity is in.
     *
     * @return true if currently viewing shelves. false if in book view.
     */
    boolean inShelfView()
    {
        return ARecyclerView.getAdapter() instanceof ShelfRecyclerViewAdapter;
    }

    /**
     * This method constructs a set of Book objects for testing. Note that the ISBNs and titles are
     * for actual books. This means that this test method can be used to verify book info retrieval.
     *
     * @return Shelf of Books
     */
    private static Shelf buildTestShelf()
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
     * Fabricates a Shelf List for testing purposes.
     *
     * @return a List containing 3 Shelf elements with 3 Books each.
     */
    public static List<Shelf> buildTestList()
    {
        // build a list of 3 shelves
        List<Shelf> testList = new ArrayList<>();
        for(int i=1;i<=3;i++)
            testList.add(buildTestShelf());
        return testList;
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
