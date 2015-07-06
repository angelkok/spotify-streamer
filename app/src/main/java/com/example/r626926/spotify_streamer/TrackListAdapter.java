package com.example.r626926.spotify_streamer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by r626926 on 7/5/15.
 */
public class TrackListAdapter extends ArrayAdapter<Track>{
    private static final String TAG = TrackListAdapter.class.getSimpleName();

    public TrackListAdapter(Activity context, int layoutId, int viewId, List<Track> trackList) {
        super(context, layoutId, viewId, trackList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Track track = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_track, parent, false);
        }
        TextView trackName = (TextView) convertView.findViewById(R.id.list_item_track_name);
        trackName.setText(track.name);

        TextView albumName = (TextView) convertView.findViewById(R.id.list_item_album_name);
        albumName.setText(track.album.name);

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_album_image);
        if (track.album.images.size() > 0) {
            int imageIndex = 1;
            Picasso.with(parent.getContext()).load(track.album.images.get(imageIndex).url.toString()).into(iconView);
        }

        return convertView;
    }

}

