package org.launchcode.g7.bookcollections;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.launchcode.g7.bookcollections.models.Book;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 *
 */

class BookReadWrite
{
    private Context context;

    BookReadWrite(Context context)
    {
        this.context=context;
    }

    /**
     * This method contains template code from the Android documentation. It's not really meant to
     * be anything but an example and model for other methods.
     */
    void saveText()
    {
        String filename = "myfile";
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

    void saveBook(Book book)
    {
        String filename = "BookFile";
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

            //TODO remove logs
            Log.d("MMMM",context.getFileStreamPath(filename).getCanonicalPath());

            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void saveCollectionXML()
    {
        String filename = "Collections.xml";

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

            Log.d("MMMM",context.getFileStreamPath(filename).getCanonicalPath());

            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    void saveCollection(Collection shelf)
    {

    }
}
