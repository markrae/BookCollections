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

import org.launchcode.g7.bookcollections.models.Shelf;

public class NewShelfFragment extends DialogFragment {
    private Button buildShelf;
    private EditText shelfEdit;
    private String shelfName;
    private OnBuildShelfClickListener onBuildShelfClickListener;

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

                onBuildShelfClickListener.onBuildShelfClick(new Shelf(shelfName));

                createNewShelf();
                removeSelf();
            }
        };
        buildShelf.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        // Verify that the host context implements the OnBuildShelfClickListener interface
        try {
            onBuildShelfClickListener = (OnBuildShelfClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void createNewShelf() {
        Shelf shelf = new Shelf(shelfName);
        //TODO add this shelf to the list of shelves and save it to file
    }

    private void removeSelf() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    /**
     * Requiring this interface means that the activity that uses this fragment must also be able
     * to listen for the clicks from the submit button of this fragment. Listening to those click
     * events will allow the host activity to pull the information from the text boxes.
     */
    public interface OnBuildShelfClickListener
    {
        void onBuildShelfClick(Shelf newShelf);
    }
}
