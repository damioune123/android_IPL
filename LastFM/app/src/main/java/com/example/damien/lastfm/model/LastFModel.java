package com.example.damien.lastfm.model;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.damien.lastfm.R;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by damien on 4/19/17.
 */

public class LastFModel {
    public static final String url = "http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=belgium&api_key=32ef5df0e36797b605e205529058f3b8&format=json&limit=50";
    public static final String error_connection = "Votre smartphone n\'est pas connecte à internet !";
    public static final String error_remote_host = "Il semble que Last FM est déconnecté";

    private LastFMNetworkServices networkServices;
    private List<LasFModelChangeObserver> observers;
    private List<Artist> artistsresults ;
    public LastFModel( ){
        this.artistsresults =  new ArrayList<>();
        this.networkServices =new LastFMNetworkServices();
        this.observers =  new ArrayList<>();
    }
    public void updateSharedArtistsResults(ConnectivityManager connMgr){
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
                this.networkServices = new LastFMNetworkServices();
                networkServices.execute(LastFModel.url);
        }
        else {
            notifyErrorAllObservers(LastFModel.error_connection);
        }
    }
    private void notifyChangeAllObservers(){
        for(LasFModelChangeObserver obs : observers){
            obs.notifyChange();
        }
    }
    private void notifyErrorAllObservers(String msgErreur){
        for(LasFModelChangeObserver obs : observers){
            obs.notifyErrorConnection(msgErreur);
        }
    }
    public List<Artist> getArtistsResults(){
        return this.artistsresults;
    }
    public void registerObserver(LasFModelChangeObserver obs){observers.add(obs);}

    public void unregister(LasFModelChangeObserver obs){
        observers.remove(obs);
    }
    public interface LasFModelChangeObserver { //sert à notifier les gens qui nous observent qu'il y a un changement
        public void notifyChange();
        public void notifyErrorConnection(String msgErreur);
    }

    private class LastFMNetworkServices extends AsyncTask<String, Void, String> {

        @Override
        public String doInBackground(String... params) {
            URL url= null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try{
                return downloadUrl(url);
            }catch (IOException io){
                Log.d("debug","erreur io");
                return "Url invalide";
            }
        }

        @Override
        public void onPostExecute(String s) {
            super.onPostExecute(s);
            artistsresults.clear();
            try{
                artistsresults.addAll(Artist.convertJsonToList(s));
            }catch (JSONException jse){
                Log.d("DEBJson", jse.getMessage());
                return;
            }
            LastFModel.this.notifyChangeAllObservers();

        }

        private String downloadUrl(URL url) throws IOException {

            InputStream stream = null;
            HttpURLConnection connection = null;
            String result = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                connection.setReadTimeout(3000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                connection.setConnectTimeout(3000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                connection.setDoInput(true);
                // Open communications link (network traffic occurs here).
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    LastFModel.this.notifyErrorAllObservers(LastFModel.error_remote_host);
                }
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();

                if (stream != null) {
                    result = readStream(stream, 1000000);
                }
            } finally {
                // Close Stream and disconnect HTTPS connection.
                if (stream != null) {
                    stream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return result;
        }
        /**
         * Converts the contents of an InputStream to a String.
         */
        private String readStream(InputStream stream, int maxLength) throws IOException {
            String result = null;
            // Read InputStream using the UTF-8 charset.
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            // Create temporary buffer to hold Stream data with specified max length.
            char[] buffer = new char[maxLength];
            // Populate temporary buffer with Stream data.
            int numChars = 0;
            int readSize = 0;
            while (numChars < maxLength && readSize != -1) {
                numChars += readSize;
                int pct = (100 * numChars) / maxLength;
                readSize = reader.read(buffer, numChars, buffer.length - numChars);
            }
            if (numChars != -1) {
                // The stream was not empty.
                // Create String that is actual length of response body if actual length was less than
                // max length.
                numChars = Math.min(numChars, maxLength);
                result = new String(buffer, 0, numChars);
            }
            return result;
        }
    }
}
