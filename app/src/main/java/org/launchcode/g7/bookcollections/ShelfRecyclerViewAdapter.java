package org.launchcode.g7.bookcollections;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.launchcode.g7.bookcollections.ItemListFragment.OnListFragmentInteractionListener;
import org.launchcode.g7.bookcollections.models.Shelf;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Shelf} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ShelfRecyclerViewAdapter extends RecyclerView.Adapter<ShelfRecyclerViewAdapter.ViewHolder>
{

    private final List<Shelf> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ShelfRecyclerViewAdapter(List<Shelf> items, OnListFragmentInteractionListener listener)
    {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener)
                {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem,holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        Shelf mItem;

        ViewHolder(View view)
        {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
