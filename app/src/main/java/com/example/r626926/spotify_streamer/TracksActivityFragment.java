package com.example.r626926.spotify_streamer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class TracksActivityFragment extends Fragment {
    private static final String TAG = TracksActivityFragment.class.getSimpleName();

    TrackListAdapter mTracksAdapter;
    FetchTracksTask fetchTracksTask;
    TracksActivity context;

    public TracksActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = (TracksActivity)getActivity();

        List<Track> tracksData = new ArrayList<Track>();

        mTracksAdapter =
                new TrackListAdapter(
                        context,
                        R.layout.list_item_track,
                        R.id.list_item_track_name,
                        tracksData
                );

        fetchTracksTask = new FetchTracksTask();
        fetchTracksTask.execute(context.artistId);

        View rootView = inflater.inflate(R.layout.fragment_tracks, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view_tracks);
        listView.setAdapter(mTracksAdapter);

        return rootView;
    }


    public class FetchTracksTask extends AsyncTask<String, Void, List<Artist>> {
        private final String TAG = FetchTracksTask.class.getSimpleName();

        @Override
        protected List<Artist> doInBackground(String... params) {
            SpotifyApi api = null;
            SpotifyService spotify = null;
            Map map = new HashMap<>();
            map.put(SpotifyService.COUNTRY, "US");

            Log.d("TAG", params[0].toString());

            if (params.length == 0) {
                return null;
            }

            try {
                api = new SpotifyApi();
                spotify = api.getService();
                spotify.getArtistTopTrack(params[0], map, new Callback<Tracks>() {
                    @Override
                    public void success(Tracks topTracks, Response response) {
                        List<Track> tracks = topTracks.tracks;
                        updateTracksAdapter(tracks);
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.toString());
                    }
                    private void updateTracksAdapter(List<Track> tracks) {

                        if (tracks.size() > 0) {
                            mTracksAdapter.clear();
                            mTracksAdapter.addAll(tracks);
                        }
                        else {
                            Toast.makeText(getActivity(), getString(R.string.no_results), Toast.LENGTH_SHORT).show();
                        }
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
