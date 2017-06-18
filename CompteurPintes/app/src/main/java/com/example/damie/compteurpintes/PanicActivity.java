package com.example.damie.compteurpintes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.health.PackageHealthStats;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

public class PanicActivity extends AppCompatActivity {
    TextView tropBuText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);
        tropBuText = (TextView) findViewById(R.id.tropBuText);
        tropBuText.setText("Vous avez trop bu !");
    }
    public void onDial(View view) {
        Intent inte = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.tel).trim()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(inte);

    }
    @Override
    public void onBackPressed() {
    }
}
