package com.ggg.hour9application;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter<ViewHolder> {

    /**
     * vars
     */
    ArrayList<Item> mItems;
    Context mContext;


    /**
     * Constructor
     */
    public ItemAdaptor(ArrayList<Item> items, Context context) {
        // pass data from activity to adaptor
        mItems = items;
        mContext = context;
    }


    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create new item view from XML resource
        View itemView = LayoutInflater
                // get context from recycler view
                .from(parent.getContext())
                .inflate(
                        // XML resource id
                        R.layout.view_item,
                        // parent of the newly inflated view (recycler view)
                        parent,
                        // whether return parent as the root view of the newly inflated view
                        false);

        // return new view holder with the new item view
        return new ViewHolder(itemView);
    }


    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     *
     * @param viewHolder The ViewHolder which should be updated to represent the item content
     * @param position   The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // get current item data and prep content formatter
        final Item currentItem = mItems.get(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        // set alternative title color
        String titleColor = (position % 2 != 0) ? "#e91e63" : "#009688";
        viewHolder.textViewName.setTextColor(Color.parseColor(titleColor));

        // set content data to views using passed in view holder
        viewHolder.textViewName.setText(currentItem.mName);
        viewHolder.textViewDesc.setText(currentItem.mDescription);
        viewHolder.textViewPrice.setText(formatter.format(currentItem.mPrice));

        // add click handler
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, currentItem.mName, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
