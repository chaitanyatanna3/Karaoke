package com.example.chaitanya.karaoke;

public class Songs {

    private String id;
    private String title;
    private String artist;
    private String duration;

    //get and set methods for ID
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    //get and set methods for title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    //get and set methods for artist
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    //get and set methods for duration
    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
}
