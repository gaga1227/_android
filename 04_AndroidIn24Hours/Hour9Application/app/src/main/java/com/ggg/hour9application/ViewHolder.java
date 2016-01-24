package com.ggg.hour9application;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    /**
     * members
     */

    public CardView cardView;
    public TextView textViewName;
    public TextView textViewPrice;
    public TextView textViewDesc;

    /**
     * Constructor
     * @param itemView
     */
    public ViewHolder(View itemView) {
        super(itemView);

        // find child views
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        textViewDesc = (TextView) itemView.findViewById(R.id.textViewDescription);
    }
}
