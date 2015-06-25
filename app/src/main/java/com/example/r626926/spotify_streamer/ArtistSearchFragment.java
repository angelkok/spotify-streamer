package com.example.r626926.spotify_streamer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


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

        FetchArtistsTask fetchArtistsTask = new FetchArtistsTask();
        fetchArtistsTask.execute("cohen");

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

    public class FetchArtistsTask extends AsyncTask<String, Void, List<Artist>> {
        private final String TAG = FetchArtistsTask.class.getSimpleName();

        @Override
        protected List<Artist> doInBackground(String... params) {
            SpotifyApi api = null;
            SpotifyService spotify = null;

            if (params.length == 0) {
                return null;
            }

            try {
                api = new SpotifyApi();
                spotify = api.getService();

                spotify.searchArtists(params[0], new Callback<ArtistsPager>() {
                    @Override
                    public void success(ArtistsPager pager, Response response) {
                        List<Artist> artists = pager.artists.items;
                        updateArtistsAdapter(artists);
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.toString());
                    }

                    private void updateArtistsAdapter(List<Artist> artists) {
                        String[] names = new String[artists.size()];
                        for(int i=0; i < artists.size(); i++ ) {
                            names[i] = artists.get(i).name;
                        }
                        mArtistsAdapter.clear();
                        mArtistsAdapter.addAll(names);
                    }

                });
            }
            catch (Exception e) {
                Log.e(TAG, "Exception: ", e);
            }
            return null;
        }
    }
}
