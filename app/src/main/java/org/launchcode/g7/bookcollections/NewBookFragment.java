package org.launchcode.g7.bookcollections;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.launchcode.g7.bookcollections.models.Book;

public class NewBookFragment extends DialogFragment {
    private Button addBook;
    private EditText editText;
    private String ISBN;
    private OnAddBookClickListener onAddBookClickListener;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        // Verify that the host context implements the OnAddBookClickListener interface
        try {
            onAddBookClickListener = (OnAddBookClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnAddBookClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_book, container, false);

        editText = view.findViewById(R.id.bookISBN);
        addBook = view.findViewById(R.id.addBook);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISBN = editText.getText().toString();
                //TODO do something with ISBN before removing fragment

                // Send the newly created book back to the listener
                onAddBookClickListener.onAddBookClick(new Book(ISBN));

                removeSelf();
            }
        };
        addBook.setOnClickListener(listener);

        return view;
    }

    private void removeSelf() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
    /**
     * Requiring this interface means that the activity that uses this fragment must also be able
     * to listen for the clicks from the submit button of this fragment. Listening to those click
     * events will allow the host activity to pull the information from the text boxes.
     */
    public interface OnAddBookClickListener
    {
        void onAddBookClick(Book newBook);
    }
}
