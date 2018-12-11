package com.example.kyotonagoya.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoViewAdaptor extends BaseAdapter {
    private LayoutInflater inflater;
    private int resourcedId;
    private ArrayList<String> items;

    static class ViewHolder {
        TextView taskName;
        Button deleteButton;
    }

    TodoViewAdaptor(Context context, int resourcedId, ArrayList<String> item) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
        this.items = item;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(resourcedId, parent, false);

            holder = new ViewHolder();
            holder.taskName = convertView.findViewById(R.id.taskName);
            holder.deleteButton = convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.taskName.setText(items.get(position));

        return convertView;
    }

}
