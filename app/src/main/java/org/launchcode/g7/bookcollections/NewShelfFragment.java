package org.launchcode.g7.bookcollections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.launchcode.g7.bookcollections.models.Shelf;

public class NewShelfFragment extends Fragment {
    private Button buildShelf;
    private EditText shelfEdit;
    private String shelfName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_shelf, container, false);

        shelfEdit = view.findViewById(R.id.shelfName);
        buildShelf = view.findViewById(R.id.buildShelf);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shelfName = shelfEdit.getText().toString();
                createNewShelf();
                removeSelf();
            }
        };
        buildShelf.setOnClickListener(listener);

        return view;
    }

    private void createNewShelf() {
        Shelf shelf = new Shelf(shelfName);
        //TODO add this shelf to the list of shelves and save it to file
    }

    private void removeSelf() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
