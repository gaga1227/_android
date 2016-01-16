package com.ggg.demos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class D005ListViewCustomRowViewHolder {

    // child views refs
    ImageView icon = null;
    TextView size = null;

    public D005ListViewCustomRowViewHolder(View row) {
        this.icon = (ImageView) row.findViewById(R.id.icon);
        this.size = (TextView) row.findViewById(R.id.size);
    }
}