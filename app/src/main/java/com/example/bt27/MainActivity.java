package com.example.bt27;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnAddAlbum, btnWatchAlbum, btnWatchSong;
    ArrayList<Album> listAlbum = new ArrayList<Album>();
    AlbumAdapter albumAdapter;
    ArrayList<Song> listSong = new ArrayList<Song>();
    SongAdapter songAdapter;
    Adapter SpinnerAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding();

        btnAddAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAddAlbum();
            }
        });
        
        btnWatchAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAlbum();
            }
        });

        btnWatchSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowSong();
            }
        });
    }

    public void binding(){
        btnAddAlbum = (Button) findViewById(R.id.btnAddAlbum);
        btnWatchAlbum = (Button) findViewById(R.id.btnWatchAlbum);
        btnWatchSong = (Button) findViewById(R.id.btnWatchSong);
    }

    private void ShowAddAlbum() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCanceledOnTouchOutside(false);

        //get value
        EditText idAlbum = (EditText) dialog.findViewById(R.id.edittextIdAlbum);
        EditText nameAlbum = (EditText) dialog.findViewById(R.id.edittextNameAlbum);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        Button btnReset = (Button) dialog.findViewById(R.id.btnReset);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idAlbum.getText().toString().trim();
                String name = nameAlbum.getText().toString().trim();
                Album albums = new Album(id, name);
                listAlbum.add(albums);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAlbum.setText("");
                nameAlbum.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void ShowAlbum() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.listview);
        dialog.setCanceledOnTouchOutside(false);

        //get value
        ListView lv = (ListView) dialog.findViewById(R.id.lvAlbum);
        Button btnExit =(Button) dialog.findViewById(R.id.btnExit);


        albumAdapter = new AlbumAdapter(this, android.R.layout.simple_expandable_list_item_1, listAlbum);
        lv.setAdapter(albumAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowUpdateAlbum(listAlbum.get(i));
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                messagebox(listAlbum.get(i));
                return false;
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void ShowUpdateAlbum(Album album) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);
        dialog.setCanceledOnTouchOutside(false);

        //get value
        EditText idAlbum = (EditText) dialog.findViewById(R.id.edittextIdAlbum);
        EditText nameAlbum = (EditText) dialog.findViewById(R.id.edittextNameAlbum);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
        Button btnReset = (Button) dialog.findViewById(R.id.btnReset);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        idAlbum.setText(album.getIdAlbum());
        nameAlbum.setText(album.getNameAlbum());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Album i : listAlbum){
                    if (i == album){
                        String id = idAlbum.getText().toString().trim();
                        String name = nameAlbum.getText().toString().trim();
                        i.setIdAlbum(id);
                        i.setNameAlbum(name);
                    }
                }
                albumAdapter.notifyDataSetChanged();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idAlbum.setText("");
                nameAlbum.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void messagebox(Album album) {
        AlertDialog.Builder mess = new AlertDialog.Builder(this);
        mess.setTitle("Remove");
        mess.setIcon(R.mipmap.ic_launcher);
        mess.setMessage("Are you want delete this album "+album.getNameAlbum()+"");
        mess.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listAlbum.remove(album);
                albumAdapter.notifyDataSetChanged();
            }
        });

        mess.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        mess.show();
    }

    private void ShowSong() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.listview_song);
        dialog.setCanceledOnTouchOutside(false);

        //get value
        ListView lvSong = (ListView) dialog.findViewById(R.id.lvSong);
        EditText edittextNameSong = (EditText) dialog.findViewById(R.id.edittextNameSong);
        EditText edittextCreateDate = (EditText) dialog.findViewById(R.id.edittextCreateDate);
        Button btnAddSong =(Button) dialog.findViewById(R.id.btnAddSong);
        Button btnExit =(Button) dialog.findViewById(R.id.btnExit);
        Spinner spinner =(Spinner) dialog.findViewById(R.id.spinner);


        albumAdapter = new AlbumAdapter(this, android.R.layout.simple_expandable_list_item_1, listAlbum);
        spinner.setAdapter(albumAdapter);

        songAdapter = new SongAdapter(this, android.R.layout.simple_expandable_list_item_1, listSong);
        lvSong.setAdapter(songAdapter);

        btnAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song = new Song(edittextNameSong.getText().toString(), edittextCreateDate.getText().toString());
                listSong.add(song);
                songAdapter.notifyDataSetChanged();
            }
        });

        edittextCreateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDatePickerDialog(edittextCreateDate);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void ShowDatePickerDialog(EditText edit) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                calendar.set(i, i1, i2);
                edit.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        pickerDialog.show();
    }
}