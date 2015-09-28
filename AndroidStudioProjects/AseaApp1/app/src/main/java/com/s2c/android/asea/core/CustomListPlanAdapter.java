package com.s2c.android.asea.core;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.s2c.android.asea.Model.Plan;
import com.s2c.android.asea.aseaapp1.R;

/**
 * Created by caom on 20/09/2015.
 */
public class CustomListPlanAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Plan> planItems;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListPlanAdapter(Activity activity, List<Plan> plansItems) {
        this.activity = activity;
        this.planItems = plansItems;
    }

    @Override
    public int getCount() {
        return planItems.size();
    }

    @Override
    public Object getItem(int location) {
        return planItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_view_plan, null);

        //if (imageLoader == null)imageLoader = AppController.getInstance().getImageLoader();
        //NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView namePlan = (TextView) convertView.findViewById(R.id.namePlan);
        TextView descriptionPlan = (TextView) convertView.findViewById(R.id.descriptionPlan);
        TextView pricePlan = (TextView) convertView.findViewById(R.id.pricePlan);


        // getting movie data for the row
        Plan m = planItems.get(position);


        // name
        namePlan.setText(m.getName());

        // description
        descriptionPlan.setText(String.valueOf(m.getDescription()));

        // release price
        pricePlan.setText(String.valueOf(m.getPrice()));

        return convertView;
    }

}