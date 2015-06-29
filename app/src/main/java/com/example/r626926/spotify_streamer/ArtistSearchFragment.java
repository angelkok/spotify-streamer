package com.example.r626926.spotify_streamer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
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

    ArtistListAdapter mArtistsAdapter;
    FetchArtistsTask fetchArtistsTask;
    SearchView searchView;
    Activity context;

    public ArtistSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();

        ArrayList<Artist> artistsData = new ArrayList<Artist>();

        mArtistsAdapter =
                new ArtistListAdapter(
                        context,
                        R.layout.list_item_artist,
                        R.id.list_item_artist_name,
                        artistsData
                );

        fetchArtistsTask = new FetchArtistsTask();
        fetchArtistsTask.execute("artist");

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_artists);
        listView.setAdapter(mArtistsAdapter);

        searchView = (SearchView)rootView.findViewById(R.id.artist_search);
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        new FetchArtistsTask().execute(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                }
        );
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
                        mArtistsAdapter.clear();
                        mArtistsAdapter.addAll(artists);
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
