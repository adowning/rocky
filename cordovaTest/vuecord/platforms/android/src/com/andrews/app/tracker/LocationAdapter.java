package com.andrews.app.tracker;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.List;

public class LocationAdapter extends BaseAdapter implements ListAdapter {

    private final Context context;
    private final List<Location> tasks;

    public LocationAdapter(Context context, List<Location> tasks) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        if (tasks == null) {
            throw new IllegalArgumentException("List of tasks must not be null.");
        }
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return Adapter.IGNORE_ITEM_VIEW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.task_item, parent, false);
        }

//        TextView desc = (TextView) convertView.findViewById(R.id.task_description);
//        CheckBox completed = (CheckBox) convertView.findViewById(R.id.checkbox_completed);

        Location t = this.tasks.get(position);
//        desc.setText(t.getDescription());
//        completed.setChecked(t.isCompleted());
//        completed.setId(position);

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    /**
     * Add the given Task at the end of the list
     */
    public void add(Location t) {
        this.tasks.add(t);
        this.notifyDataSetChanged();
    }

    /**
     * Put the give Task at specified position
     */
    public void set(int position, Location t) {
        this.tasks.set(position, t);
        this.notifyDataSetChanged();
    }

    /**
     * Remove the Task at specified position
     */
    public void remove(int position) {
        this.tasks.remove(position);
    }
}