package com.example.damien.lastfm.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.example.damien.lastfm.R;
import com.example.damien.lastfm.model.Artist;

public class MainActivity extends FragmentActivity implements ArtistListFragment.OnArtistSelecedListenner {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (findViewById(R.id.artist_detail_container) != null)
            mTwoPane = true;
        if(!mTwoPane){
            ArtistListFragment artistListFragment = new ArtistListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, artistListFragment);
            ft.commit();
        }
    }

    @Override
    public void onArtistSelected(Artist art) {
        final Bundle arguments = new Bundle();
        final ArtistDetailFragment fragment = new ArtistDetailFragment();
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        arguments.putString(ArtistDetailFragment.ARG_ITEM_ID, art.getUrl());
        fragment.setArguments(arguments);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (mTwoPane)
            ft.replace(R.id.artist_detail_container, fragment);
         else
            ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
