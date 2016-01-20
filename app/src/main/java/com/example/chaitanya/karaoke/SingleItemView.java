package com.example.chaitanya.karaoke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends Activity {

    //declare variables
    String id;
    String title;
    String artist;
    String duration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        title = i.getStringExtra("title");
        artist = i.getStringExtra("artist");
        duration = i.getStringExtra("duration");

        TextView txt_id = (TextView) findViewById(R.id.Id);
        TextView txt_title = (TextView) findViewById(R.id.title);
        TextView txt_artist = (TextView) findViewById(R.id.artist);
        TextView txt_duration = (TextView) findViewById(R.id.duration);

        txt_id.setText(id);
        txt_title.setText(title);
        txt_artist.setText(artist);
        txt_duration.setText(duration);
    }
}
