package com.example.chaitanya.karaoke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    //Declare Variables
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mPDialog;
    static String ID = "id";
    static String TITLE = "title";
    static String ARTIST = "artist";
    static String DURATION = "duration";
    EditText editsearch;
    List<Songs> songslist = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        new DownloadXML().execute();
    }

    //DownloadXML AsyncTask
    private class DownloadXML extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPDialog = new ProgressDialog(MainActivity.this);
            mPDialog.setTitle("Karaoke");
            mPDialog.setMessage("Loading...");
            mPDialog.setIndeterminate(false);
            mPDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //create the array
            songslist = new ArrayList<Songs>();
            //parse xml from xml asset file
            XMLParser parser = new XMLParser();
            try {
                InputStream is = getAssets().open("songs1.xml");
                songslist = parser.parse(is);
//                ArrayAdapter<Songs> aadpater = new ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1, songslist);
//                listview.setAdapter(aadpater);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Songs map = new Songs();
                map.setId(parser.getValue(ID));
                map.setTitle(parser.getValue(TITLE));
                map.setArtist(parser.getValue(ARTIST));
                map.setDuration(parser.getValue(DURATION));
                songslist.add(map);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args){

            //locate the listview in xml file
            listview = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(MainActivity.this, songslist);
            listview.setAdapter(adapter);
            mPDialog.dismiss();
            //locate the edittext in xml file
            editsearch = (EditText) findViewById(R.id.search);
            //capture text in edittext
            editsearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                    adapter.filter(text);
                }
            });

        }
    }
}
