package org.launchcode.g7.bookcollections;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.launchcode.g7.bookcollections.models.Book;
import org.launchcode.g7.bookcollections.models.Shelf;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Object that handles saving and reading the List of Shelves from the file.
 */
class BookReadWrite
{
    private Context context;

    BookReadWrite(Context context)
    {
        this.context = context;
    }

    /**
     * Saves one file of all Shelves containing all saved books.
     *
     * @param allShelves a list containing all the book shelves.
     */
    void saveShelves(List<Shelf> allShelves)
    {
        try
        {
            FileOutputStream fileOut = context.openFileOutput(context.getString(R.string.filename),Context.MODE_PRIVATE);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            objOut.writeObject(allShelves);

            objOut.close();
            fileOut.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes all Shelves from file into Objects.
     *
     * @return Warning: may return null if unable to access file.
     */
    List<Shelf> readShelves()
    {
        List<Shelf> deserializedShelf = null;

        try
        {
            FileInputStream fileIn = context.openFileInput(context.getString(R.string.filename));
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            deserializedShelf = (ArrayList<Shelf>) objIn.readObject();

            objIn.close();
            fileIn.close();


        }
        catch (InvalidClassException ice)
        {
            // This exception occurs upon a deserialization that has mismatched serialVersionUIDs
            // In such an event, the old data is lost basically. Return fresh data.
            deserializedShelf = MainActivity.buildTestList();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // if deserializedShelf is null, then log and return valid data anyway.
            if (deserializedShelf==null)
            {
                //TODO remove logs in final version
                Log.d("BRWtest", "Didn't read a file. Returning test data.");
                deserializedShelf = MainActivity.buildTestList();
            }
            // Otherwise, output the name of the first Shelf to the log
            else
            {

                //TODO remove logs in final version
                Log.d("BRWtest", deserializedShelf.get(0).getName());
            }
        }
        return deserializedShelf;
    }

    /**
     * This method contains template code from the Android documentation. It's not really meant to
     * be anything but an example and model for other methods.
     *
     * @deprecated
     */
    void saveText()
    {
        String filename = context.getString(R.string.filename);
        String fileContents = "Hello world!";
        FileOutputStream outputStream;

        try
        {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Stores a single book in the file and was previously used for testing.
     *
     * @deprecated
     * @param book Book object to be saved.
     */
    void saveBook(Book book)
    {
        String filename = context.getString(R.string.filename);
        FileOutputStream outputStream;
        ObjectOutputStream objStream;

        try
        {
            //initialize fileoutput
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            // initialize ObjectOutputStream
            objStream = new ObjectOutputStream(outputStream);

            // write book object
            objStream.writeObject(book);

            //TODO remove logs in final version
            Log.d("MMMM",context.getFileStreamPath(filename).getCanonicalPath());

            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Example method for saving XML data in case we decide to transition to this later.
     *
     * @deprecated
     */
    void saveShelfXML()
    {
        String filename = context.getString(R.string.filename);

        FileOutputStream fos;

        try
        {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);

            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            serializer.startTag(null, "root");

            for (int j = 0; j < 3; j++)
            {

                serializer.startTag(null, "record");

                serializer.text(Integer.toString(j));

                serializer.endTag(null, "record");
            }
            serializer.endDocument();

            serializer.flush();
            //TODO remove logs in final version
            Log.d("MMMM",context.getFileStreamPath(filename).getCanonicalPath());

            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
