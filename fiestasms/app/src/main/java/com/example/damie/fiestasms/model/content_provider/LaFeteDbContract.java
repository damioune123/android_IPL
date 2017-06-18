package com.example.damie.fiestasms.model.content_provider;

import android.provider.BaseColumns;

/**
 * Created by damie on 30/03/2017.
 */

public class LaFeteDbContract {
    public LaFeteDbContract() {
    }
    public static abstract class LaFeteEntry implements BaseColumns{
        public static final String  NOM_DB ="FeteDB.db";
        public static final int  VERSION_DB =1;
        public static final String  NOM_TABLE ="tableParticipations";
        public static final String  COLONNE_ID ="_id";
        public static final String  COLONNE_NOM = "nom";
        public static final String  COLONNE_PRENOM ="prenom";
        public static final String  COLONNE_BOISSON ="boisson";
        public static final String CREATION_TABLE = "create table "+ NOM_TABLE+" ( "+COLONNE_ID+ " integer primary key autoincrement, "+
                COLONNE_NOM+ " varchar(255), "+ COLONNE_PRENOM+ " varchar(255), "+ COLONNE_BOISSON +" varchar(255));";
        public static final String DROP_TABLE = "DROP TABLE"+ NOM_DB+"."+NOM_TABLE;

    }
}
