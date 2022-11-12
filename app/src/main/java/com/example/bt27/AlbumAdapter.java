package com.example.bt27;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {
    Activity context;
    ArrayList<Album> list;

    public AlbumAdapter(Activity context, int simple_expandable_list_item_1, ArrayList<Album> list) {
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
        View row=inflater.inflate(R.layout.custom_layout,null);

        TextView textNumber=(TextView)row.findViewById(R.id.textNumber);
        TextView textIdAlbum=(TextView)row.findViewById(R.id.textIdAlbum);
        TextView textNameAlbum=(TextView)row.findViewById(R.id.textNameAlbum);
        textNumber.setText((i+1)+"");
        textIdAlbum.setText(list.get(i).getIdAlbum());
        textNameAlbum.setText(list.get(i).getNameAlbum());
        return row;
    }

}
