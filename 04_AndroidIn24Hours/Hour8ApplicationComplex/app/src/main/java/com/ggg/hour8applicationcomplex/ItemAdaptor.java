package com.ggg.hour8applicationcomplex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class ItemAdaptor extends BaseAdapter{

    /**
     * vars
     */

    Context mContext;
    ArrayList<Item> mItems;
    LayoutInflater mInflater;


    /**
     * Constructor
     */

    public ItemAdaptor(Context c, ArrayList<Item> items) {
        // pass params from activity to class
        mContext = c;
        mItems = items;

        // get layout inflator service from passed in context
        // this helps turn a layout resource file into a view object
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mItems.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose
     *                    view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible
     *                    to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this
     *                    View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // create view holder instance
        ViewHolder viewHolder;

        // if is not reusing existing recycled view
        if (convertView == null) {
            // create brand new view from resource file
            convertView = mInflater.inflate(R.layout.view_item, null);

            // create view holder object to hold text view refs
            viewHolder = new ViewHolder();

            // find textViews and add refs into view holder
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.textViewPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
            viewHolder.textViewDesc = (TextView) convertView.findViewById(R.id.textViewDescription);

            // attach view holder object onto view
            convertView.setTag(viewHolder);
        }
        // if is reusing existing recycled view
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // get current item and content formatter
        Item currentItem = (Item) this.getItem(position);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        // use refs from view holder to populate recycled view
        // instead of always creating new ones
        viewHolder.textViewName.setText(currentItem.mName);
        viewHolder.textViewDesc.setText(currentItem.mDescription);
        viewHolder.textViewPrice.setText(formatter.format(currentItem.mPrice));

        //return group
        return convertView;
    }
}
