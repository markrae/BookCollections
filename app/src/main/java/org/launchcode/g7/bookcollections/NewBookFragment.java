package org.launchcode.g7.bookcollections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NewBookFragment extends Fragment{
    private Button addBook;
    private EditText editText;
    private String ISBN;

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
                removeSelf();
            }
        };
        addBook.setOnClickListener(listener);

        return view;
    }

    private void removeSelf() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
