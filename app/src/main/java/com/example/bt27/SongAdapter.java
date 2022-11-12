package com.example.bt27;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Song> list;

    public SongAdapter(Activity context, int simple_expandable_list_item_1, ArrayList<Song> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(R.layout.listsong_custom,null);

        TextView textNumber=(TextView)row.findViewById(R.id.textNumber);
        TextView textNameSong=(TextView)row.findViewById(R.id.textNameSong);
        TextView textCreateDate=(TextView)row.findViewById(R.id.textCreateDate);
        textNumber.setText((i+1)+"");
        textNameSong.setText(list.get(i).getName());
        textCreateDate.setText(list.get(i).getCreateDate()+"");
        return row;
    }
}
