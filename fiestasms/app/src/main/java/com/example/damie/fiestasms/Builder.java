package com.example.damie.fiestasms;

import android.app.Application;
import android.database.Cursor;

import com.example.damie.fiestasms.model.content_provider.LaFeteDbHelper;
import com.example.damie.fiestasms.model.ParticipantModel;

/**
 * Created by damien on 29.03.17.
 */
public class Builder extends Application{
    private ParticipantModel participantModel;
    private LaFeteDbHelper laFeteDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        laFeteDbHelper = new LaFeteDbHelper(getApplicationContext());
        participantModel = new ParticipantModel(laFeteDbHelper);
    }

    public Cursor getSharedCursor() {return laFeteDbHelper.getCursor();}
    public ParticipantModel getParticipantModel(){return participantModel;}
}
