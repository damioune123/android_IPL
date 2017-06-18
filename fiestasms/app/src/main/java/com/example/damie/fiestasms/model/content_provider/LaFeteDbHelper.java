package com.example.damie.fiestasms.model.content_provider;

import android.content.ContentValues;
import android.content.Context;

import com.example.damie.fiestasms.model.Participant;

/**
 * Created by damien on 30.03.17.
 */
public class LaFeteDbHelper extends DbHelper{

    public LaFeteDbHelper(Context context) {
        super(context);
    }
    public void fetchLast10(){
        if(cursor ==null || cursor.isClosed())
            cursor= db.rawQuery("SELECT * FROM "+ LaFeteDbContract.LaFeteEntry.NOM_TABLE+
                    " order by "+ LaFeteDbContract.LaFeteEntry.COLONNE_ID +" DESC limit 10", null);
        else
            cursor.requery();
    }

    public void insertParticipant(Participant participant){
        ContentValues values = new ContentValues();
        values.put(LaFeteDbContract.LaFeteEntry.COLONNE_NOM, participant.getNom());
        values.put(LaFeteDbContract.LaFeteEntry.COLONNE_PRENOM, participant.getPrenom());
        values.put(LaFeteDbContract.LaFeteEntry.COLONNE_BOISSON, participant.getBoisson());
        db.insert(LaFeteDbContract.LaFeteEntry.NOM_TABLE, null, values );
    }

}
