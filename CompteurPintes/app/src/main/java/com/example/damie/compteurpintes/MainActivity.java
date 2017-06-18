package com.example.damie.compteurpintes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private int compteur;
    private int record;
    private int marge;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        loadState();
        TextView recordActuel = (TextView)findViewById(R.id.record);
        recordActuel.setText(Integer.toString(record));
        TextView compteurActuel = (TextView)findViewById(R.id.incrAff);
        compteurActuel.setText(Integer.toString(compteur));
        TextView affichageMarge = (TextView)findViewById(R.id.affMarge);
        affichageMarge.setText(Integer.toString(marge));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadState();
        setContentView(R.layout.activity_main);
        TextView recordLabel = (TextView)findViewById(R.id.recordLabel);
        recordLabel.setText(R.string.record);
        TextView margeLabel = (TextView)findViewById(R.id.margeLabel);
        margeLabel.setText(R.string.marge);
        TextView  affichageCompteur = (TextView )findViewById(R.id.incrAff);
        affichageCompteur.setText(Integer.toString(compteur));
        TextView  affichageRecord = (TextView )findViewById(R.id.record);
        affichageRecord.setText(Integer.toString(record));
        TextView  affichageTexte = (TextView)findViewById(R.id.affText);
        affichageTexte.setText("Bienvenue !");
        TextView affichageMarge = (TextView)findViewById(R.id.affMarge);
        affichageMarge.setText(Integer.toString(marge));
    }
    public void onRestart(View view){
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_compt, menu);
        return true;
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == NewRecordActivity.RESULT_OK){
                String result=data.getStringExtra("marge");
                this.marge=this.record+Integer.parseInt(result);
                TextView affichageMarge = (TextView)findViewById(R.id.affMarge);
                affichageMarge.setText(Integer.toString(marge));
            }
            if (resultCode == NewRecordActivity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                finish();
                return true;
            default: super.onOptionsItemSelected(item);
        }
        return false;
    }

    public void onReset(View view){
        this.compteur=0;
        TextView  affichageCompteur = (TextView)findViewById(R.id.incrAff);
        TextView  affichageTexte = (TextView)findViewById(R.id.affText);
        TextView  affichageMarge = (TextView)findViewById(R.id.affMarge);
        TextView  affichageRecord = (TextView)findViewById(R.id.record);
        affichageTexte.setText("Compteurs remis à 0");
        affichageCompteur.setText(Integer.toString(compteur));
        affichageMarge.setText(Integer.toString(marge));
        affichageRecord.setText(Integer.toString(record));
    }

    public void onIncrement(View view){
        compteur++;
        TextView  affichageCompteur = (TextView)findViewById(R.id.incrAff);
        TextView  affichageTexte = (TextView)findViewById(R.id.affText);
        TextView recordActuel = (TextView)findViewById(R.id.record);
        String toShow="";

        if(compteur > record){
            toShow+="Nouveau record ! ";
            record = compteur;
            recordActuel.setText(Integer.toString(record));
            if(marge < compteur){
                Intent intent = new Intent(MainActivity.this, NewRecordActivity.class);
                startActivityForResult(intent, 1);
            }
            if(marge ==compteur){
                Intent intent = new Intent(MainActivity.this, PanicActivity.class);
                startActivity(intent);

            }
        }
        affichageCompteur.setText(Integer.toString(compteur));
        switch (compteur){
            case 1: toShow+=getResources().getString(R.string.biere_1);
                    affichageTexte.setText(toShow);
                     break;
            case 5: toShow+=getResources().getString(R.string.biere_5);
                    affichageTexte.setText(toShow);
                    break;
            case 8: toShow+=getResources().getString(R.string.biere_8);
                    affichageTexte.setText(toShow);
                    break;
            case 10: toShow+=getResources().getString(R.string.biere_10);
                    affichageTexte.setText(toShow);
                    break;
            case 12:toShow+=getResources().getString(R.string.biere_12);
                    affichageTexte.setText(toShow);
                    break;
            case 15: toShow+=getResources().getString(R.string.biere_15);
                    affichageTexte.setText(toShow);
                    break;
            case 18:toShow+=getResources().getString(R.string.biere_18);
                    affichageTexte.setText(toShow);
                    break;
            case 24: toShow+=getResources().getString(R.string.biere_24);
                    affichageTexte.setText(toShow);
                    break;
            case 30: toShow+=getResources().getString(R.string.biere_30);
                    affichageTexte.setText(toShow);
                    break;
            case 50: toShow+=getResources().getString(R.string.biere_50);
                    affichageTexte.setText(toShow);
                    break;
            default: toShow+="Une de plus !";
                    affichageTexte.setText(toShow);
                    break;
        }
    }
    public void saveState(){
        SharedPreferences preferences = this.getSharedPreferences("mesPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("compteur",compteur);
        editor.putInt("record",record);
        editor.putInt("marge",marge);
        editor.commit();

    }

    public void loadState(){
        SharedPreferences preferences = this.getSharedPreferences("mesPreferences", MODE_PRIVATE);
        this.compteur = preferences.getInt("compteur",0);
        this.record = preferences.getInt("record",0);
        this.marge = preferences.getInt("marge",0);

    }


}
