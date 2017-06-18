package com.example.damien.lastfm;

import android.app.Application;

/**
 * Created by damien on 4/19/17.
 */

import com.example.damien.lastfm.model.Artist;
import com.example.damien.lastfm.model.LastFModel;


import java.util.ArrayList;

public class Builder extends Application {
    private LastFModel model;
    @Override
    public void onCreate() {
        super.onCreate();
        model = new LastFModel();
    }
    public LastFModel getModel() {
        return model;
    }
}
