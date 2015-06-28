package com.example.r626926.spotify_streamer;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by r626926 on 6/28/15.
 */
public class ArtistListAdapter extends ArrayAdapter<String> {

    public ArtistListAdapter(Activity context, int layoutId, int viewId, List<String> artistList) {
        super(context, layoutId, viewId, artistList);
    }
}
