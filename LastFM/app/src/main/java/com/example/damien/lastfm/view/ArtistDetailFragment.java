package com.example.damien.lastfm.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.damien.lastfm.R;

/**
 * Created by damien on 15/06/2017.
 */

public class ArtistDetailFragment extends Fragment {
    private String artistUrl;
    public static final String ARG_ITEM_ID = "artist_detail_id";

    public ArtistDetailFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments().containsKey(ARG_ITEM_ID))
            artistUrl = getArguments().getString(ARG_ITEM_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_artist_detail, container, false);
        if(artistUrl==null)
            return rootView;
        WebView mWebView = (WebView) rootView.findViewById(R.id.artist_detail);
        mWebView.loadUrl(artistUrl);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        return rootView;
    }
}
