package com.example.chaitanya.karaoke;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

    //Declare variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    private List<Songs> songsList = null;
    private ArrayList<Songs> arrayList;

    public ListViewAdapter(Context context, List<Songs> songsList) {
        this.context = context;
        this.songsList = songsList;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<Songs>();
        this.arrayList.addAll(songsList);
    }

    public class ViewHolder{
        TextView id;
        TextView title;
        TextView artist;
        TextView duration;
    }

    @Override
    public int getCount() {
        return songsList.size();
    }

    @Override
    public Object getItem(int position) {
        return songsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);

            holder.id = (TextView) view.findViewById(R.id.Id);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.artist = (TextView) view.findViewById(R.id.artist);
            holder.duration = (TextView) view.findViewById(R.id.duration);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        //set the results into textview
        holder.id.setText(songsList.get(position).getId());
        holder.title.setText(songsList.get(position).getTitle());
        holder.artist.setText(songsList.get(position).getArtist());
        holder.duration.setText(songsList.get(position).getDuration());

        //listen for listview item click
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra("id", songsList.get(position).getId());
                intent.putExtra("title", songsList.get(position).getTitle());
                intent.putExtra("artist", songsList.get(position).getArtist());
                intent.putExtra("duration", songsList.get(position).getDuration());
                context.startActivity(intent);
            }
        });
        return view;
    }

    //filter class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        songsList.clear();
        if (charText.length() == 0) {
            songsList.addAll(arrayList);
        }
        else {
            for (Songs sg : arrayList) {
                // added for artist and album
                // by Gopal and Nilesh
                if (sg.getTitle().toLowerCase(Locale.getDefault()).contains(charText) ||
                   (sg.getArtist().toLowerCase(Locale.getDefault()).contains(charText)) )
                {
                    songsList.add(sg);
                }

            }
        }
        notifyDataSetChanged();
    }
}
