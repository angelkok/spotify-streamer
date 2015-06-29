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

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by r626926 on 6/28/15.
 */
public class ArtistListAdapter extends ArrayAdapter<Artist> {
    private static final String TAG = ArtistListAdapter.class.getSimpleName();

    public ArtistListAdapter(Activity context, int layoutId, int viewId, List<Artist> artistList) {
        super(context, layoutId, viewId, artistList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Artist artist = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_artist, parent, false);
        }
        TextView artistName = (TextView) convertView.findViewById(R.id.listview_artist_name);
        artistName.setText(artist.name);

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_artist_image);
        if (artist.images.size() > 0) {
            int imageIndex = artist.images.size() - 1;
            Picasso.with(parent.getContext()).load(artist.images.get(imageIndex).url.toString()).into(iconView);
        }

        return convertView;
    }

}
