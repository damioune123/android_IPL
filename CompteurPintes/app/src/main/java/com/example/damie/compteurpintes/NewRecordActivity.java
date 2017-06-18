package com.example.damie.compteurpintes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewRecordActivity extends AppCompatActivity {
    private int marge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        TextView  affichageTexte = (TextView)findViewById(R.id.nouveauRecordText);
        affichageTexte.setText("Nouveau Record !");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:onBackToMain();
                return true;
        }
    }

    public void onBackToMain(){

        TextView valeurMarge = (TextView) findViewById(R.id.marge);
        if(valeurMarge.getText().toString() == null || valeurMarge.getText().toString().equals("")){
            this.marge=10;
        }
        else{
            int marge =  Integer.parseInt(valeurMarge.getText().toString());
            this.marge = marge;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("marge",Integer.toString(marge));
        setResult(NewRecordActivity.RESULT_OK,returnIntent);
        finish();
    }



}
