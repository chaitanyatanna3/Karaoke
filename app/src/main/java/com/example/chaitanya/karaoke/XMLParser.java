package com.example.chaitanya.karaoke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLParser {

    private List<Songs> songs = new ArrayList<Songs>();
    private Songs song;
    private String text;

    public List<Songs> getSongs() {
        return songs;
    }

    public List<Songs> parse(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: if (tagname.equalsIgnoreCase("song")) {
                        song = new Songs();
                    }
                        break;
                    case XmlPullParser.TEXT: text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("song")) {
                            songs.add(song);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            song.setId(text);
                        } else if (tagname.equalsIgnoreCase("title")) {
                            song.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("artist")) {
                            song.setArtist(text);
                        } else if (tagname.equalsIgnoreCase("duration")) {
                            song.setDuration(text);
                        }
                        break;
                    default: break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    // Added by Gopal and Nilesh - why need to add this?
    public String getValue(String duration) {
    return duration;
    }
}
