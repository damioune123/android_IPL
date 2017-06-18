package com.example.damie.fiestasms.views;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.damie.fiestasms.Builder;
import com.example.damie.fiestasms.R;
import com.example.damie.fiestasms.model.content_provider.LaFeteDbContract;
import com.example.damie.fiestasms.model.ParticipantModel;
import android.support.v4.widget.SimpleCursorAdapter;

public class DashboardActivity extends AppCompatActivity implements ParticipantModel.SMSModelChangeObserver{

    private ParticipantModel model;
    private SimpleCursorAdapter adapter;
    private final int[] toViews = {
            R.id.nom,
            R.id.prenom,
            R.id.boisson
    };
    private final String[] fromColumns = {
            LaFeteDbContract.LaFeteEntry.COLONNE_NOM,
            LaFeteDbContract.LaFeteEntry.COLONNE_PRENOM,
            LaFeteDbContract.LaFeteEntry.COLONNE_BOISSON
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        model = ((Builder)getApplication()).getParticipantModel();
        model.registerObserver(this);
        adapter = new SimpleCursorAdapter(this, R.layout.participant, ((Builder)getApplication()).getSharedCursor(), fromColumns, toViews, 5);
        ((ListView)findViewById(R.id.listViewParticipants)).setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS))
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        model.registerObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        model.unregister(this);
    }

    @Override
    public void notifyChange() {
        model.updateParticipantCursor();
        adapter.notifyDataSetChanged();
    }

}
