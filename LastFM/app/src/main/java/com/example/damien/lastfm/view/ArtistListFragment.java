package com.example.damien.lastfm.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;


import com.example.damien.lastfm.Builder;
import com.example.damien.lastfm.R;
import com.example.damien.lastfm.model.Artist;
import com.example.damien.lastfm.model.LastFModel;

import java.util.List;

/**
 * Created by damien on 18/06/2017.
 */

public class ArtistListFragment extends Fragment implements LastFModel.LasFModelChangeObserver {
    private ArtistAdapter adapter;
    private LastFModel model;
    private OnArtistSelecedListenner mListener;
    private View.OnClickListener refreshListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            model.updateSharedArtistsResults((ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE));
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_artist_list, container, false);
        ((ListView) rootView.findViewById(R.id.listViewId)).setAdapter(adapter);
        ((Button) rootView.findViewById(R.id.buttonRefresh)).setOnClickListener(refreshListener);
        Log.i("ici", "yo");
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ((Builder) getActivity().getApplication()).getModel();
        adapter = new ArtistAdapter(this.getActivity(), model.getArtistsResults());
        model.updateSharedArtistsResults((ConnectivityManager)this.getActivity().getSystemService(CONNECTIVITY_SERVICE));
        Log.i("ici", "yaya");
    }

    @Override
    public void notifyChange() {
        adapter.notifyDataSetChanged();
        Toast toast = Toast.makeText(this.getActivity(), "Liste mise à jour !", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void notifyErrorConnection(String msgErreur) {
        Toast toast = Toast.makeText(this.getActivity(), msgErreur, Toast.LENGTH_LONG);
        toast.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        model.registerObserver(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        model.unregister(this);
    }

    public interface OnArtistSelecedListenner{
        void onArtistSelected(Artist art);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnArtistSelecedListenner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSelectedArtistListenner");
        }
    }

    private class ArtistAdapter extends ArrayAdapter<Artist> {
        public ArtistAdapter(Context context, List<Artist> artists) {
            super(context, 0, artists);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position

            Artist artist = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.artist_list_child_content, parent, false);
            }
            convertView.setMinimumHeight(100);
            if (Integer.valueOf(artist.getRank()) % 2 == 0) {
                convertView.setBackgroundColor(getResources().getColor(R.color.colorLV));
            } else {
                convertView.setBackgroundColor(getResources().getColor(R.color.colorLVBis));
            }
            FrameLayout frameL = (FrameLayout) convertView.findViewById(R.id.childArtistListItem);
            frameL.setTag(artist);
            frameL.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Artist art = (Artist) v.getTag();
                    mListener.onArtistSelected(art);
                    return true;
                }
            });
            TextView tvRank = (TextView) convertView.findViewById(R.id.rank_item);
            TextView tvContent = (TextView) convertView.findViewById(R.id.content_item);
            tvRank.setText(artist.getRank());
            tvContent.setText(artist.getContent());
            return convertView;
        }

    }
}

