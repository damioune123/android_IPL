package com.example.damie.fiestasms.model.content_provider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.damie.fiestasms.R;

/**
 * Created by damien on 14/06/2017.
 */

public class LaFeteAdapter extends CursorAdapter{
    public LaFeteAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.participant, parent, false);
    }
    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView partNomTV = (TextView) view.findViewById(R.id.nom);
        TextView partPrenomTV = (TextView) view.findViewById(R.id.prenom);
        TextView partBoissonTV = (TextView) view.findViewById(R.id.boisson);
        String partNomS = cursor.getString(cursor.getColumnIndexOrThrow(LaFeteDbContract.LaFeteEntry.COLONNE_NOM));
        String partBoissonS = cursor.getString(cursor.getColumnIndexOrThrow(LaFeteDbContract.LaFeteEntry.COLONNE_BOISSON));
        String partPrenomS = cursor.getString(cursor.getColumnIndexOrThrow(LaFeteDbContract.LaFeteEntry.COLONNE_PRENOM));
        partNomTV.setText(partNomS);
        partPrenomTV.setText(partPrenomS);
        partBoissonTV.setText(partBoissonS);
    }
}
