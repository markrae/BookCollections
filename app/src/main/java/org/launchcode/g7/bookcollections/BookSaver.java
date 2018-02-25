package org.launchcode.g7.bookcollections;

import android.content.Context;
import java.io.FileOutputStream;

/**
 *
 */

class BookSaver
{
    private Context context;

    BookSaver(Context context)
    {
        this.context=context;
    }

    void SaveText()
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
}
