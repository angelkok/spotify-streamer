package com.example.r626926.spotify_streamer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistSearchFragment extends Fragment {
    private static final String TAG = ArtistSearchFragment.class.getSimpleName();

    ArrayAdapter<String> mArtistsAdapter;

    public ArtistSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] data = {
                "Artist 1",
                "Artist 2",
                "Artist 3",
                "Artist 4",
                "Artist 5",
                "Artist 6",
                "Artist 7",
                "Artist 8",
                "Artist 9",
                "Artist 10",
                "Artist 11",
        };
        List<String> artistsData = new ArrayList<String>(Arrays.asList(data));

        mArtistsAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_artists,
                        R.id.listview_artist,
                        artistsData
                );

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_artists);
        listView.setAdapter(mArtistsAdapter);
        return rootView;
    }
}
