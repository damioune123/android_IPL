package com.example.damie.fiestasms.model.content_provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by damien on 15/06/2017.
 */
public abstract class DbHelper extends SQLiteOpenHelper {
    protected SQLiteDatabase db;
    protected Cursor cursor;
    public DbHelper(Context context){
        super(context, LaFeteDbContract.LaFeteEntry.NOM_DB, null, LaFeteDbContract.LaFeteEntry.VERSION_DB);
        open();
    }
    public abstract void fetchLast10() ;

    public void open(){
        if(db==null || !db.isOpen())
            db =this.getWritableDatabase();
    }

    public void close(){
        if(cursor != null && !cursor.isClosed())
            cursor.close();
        db.close();
    }

    public Cursor getCursor() {
        fetchLast10();
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("creaDB",LaFeteDbContract.LaFeteEntry.CREATION_TABLE );
        db.execSQL(LaFeteDbContract.LaFeteEntry.CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(LaFeteDbContract.LaFeteEntry.DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
