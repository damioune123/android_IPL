package com.example.damien.lastfm.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by damien on 4/28/17.
 */

public class Artist{

    private String url;
    private String content;
    private String mbdi;
    private String rank;

    public Artist(String url, String content, String mbid, String rank) {
        this.url = url;
        this.content = content;
        this.mbdi = mbid;
        this.rank = rank;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMbdi() {
        return mbdi;
    }

    public void setMbdi(String mbdi) {
        this.mbdi = mbdi;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public static ArrayList<Artist> convertJsonToList(String jsonToParse) throws JSONException {
        ArrayList<Artist> results = new ArrayList<>();
        JSONArray artists = new JSONObject(jsonToParse).getJSONObject("topartists").getJSONArray("artist");
        for (int i = 0; i < artists.length(); i++) {//TODO
            JSONObject artist = artists.getJSONObject(i);
            Artist aItem = new Artist(artist.getString("url"),
                    artist.getString("name") + "---" + artist.getString("listeners"),
                    artist.getString("mbid"), "" + (i+1));
            results.add(aItem);
        }
        return results;

    }
}
