package com.example.len_den;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Splitlistadapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<SplitManager> splitlist;

    public Splitlistadapter(Context context, int layout, ArrayList<SplitManager> splitlist) {
        this.context = context;
        this.layout = layout;
        this.splitlist = splitlist;
    }

    @Override
    public int getCount() {
        return splitlist.size();
    }

    @Override
    public Object getItem(int position) {
        return splitlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView name, cost,number;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();


        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.name = (TextView) row.findViewById(R.id.txtName);
            holder.cost = (TextView) row.findViewById(R.id.cost_item);
            holder.number = (TextView) row.findViewById(R.id.txtNum);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        SplitManager splitManager = splitlist.get(position);

        holder.name.setText(splitManager.getName());
        holder.number.setText(splitManager.getNumber());
        holder.cost.setText(splitManager.getPrice());
        return row;
    }
}
