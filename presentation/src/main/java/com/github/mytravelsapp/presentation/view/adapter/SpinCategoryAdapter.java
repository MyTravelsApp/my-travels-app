package com.github.mytravelsapp.presentation.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mytravelsapp.presentation.model.CategoryModel;

import java.util.List;

/**
 * Created by stefani on 24/01/2016.
 */
public class SpinCategoryAdapter extends ArrayAdapter<CategoryModel> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<CategoryModel> values;

    public SpinCategoryAdapter(Context context, List<CategoryModel> objects) {
        super(context, android.R.layout.simple_spinner_item, objects);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.context = context;
        this.values = objects;

    }

    public int getCount(){
        return values.size();
    }

    public CategoryModel getItem(int position){
        return values.get(position);
    }

    @Override
    public int getPosition(CategoryModel item) {
        return values.indexOf(item);
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(values.get(position).getName());

        return label;
    }

    public void setList(List<CategoryModel> pList){
        values = pList;
        //Add object void for category default
        values.add(new CategoryModel(CategoryModel.DEFAULT_ID,"",true));
        notifyDataSetChanged();
    }
}
