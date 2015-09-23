package com.anhnguyen.spotifypopularsongs.ui.adapters;

import com.anhnguyen.spotifypopularsongs.R;
import com.anhnguyen.spotifypopularsongs.model.SongModel;
import com.anhnguyen.spotifypopularsongs.utils.ILog;
import com.bumptech.glide.Glide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PopularSongsAdapter extends ArrayAdapter<SongModel>{

    private static final String TAG = "PopularSongsAdapter";

    private List<SongModel> songs;
    private final Activity context;

    public PopularSongsAdapter(Activity context, List<SongModel> songs) {
        super(context, 0, songs);
        this.context = context;
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public SongModel getItem(int position) {
        return songs.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.song_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.imv = (ImageView) view.findViewById(R.id.imv);
            viewHolder.tv1 = (TextView) view.findViewById(R.id.tv1);
            viewHolder.tv2 = (TextView) view.findViewById(R.id.tv2);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.bind(getItem(position));
        ILog.d(TAG, "song name " + songs.get(position).getArtistName());

        return view;
    }

    public void setListSongs(List<SongModel> listSongs) {
        songs = listSongs;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        protected ImageView imv;
        protected TextView tv1;
        protected TextView tv2;

        public void bind(SongModel song){
            if(song ==  null) return;
            String url = song.getArtworkUrl();
            Glide.with(imv.getContext()).load(url)
                .centerCrop()
                .placeholder(R.color.transparent)
                .crossFade()
                .into(imv);

            tv1.setText("" + song.getTrackName());
            tv2.setText("" + song.getArtistName());
        }
    }

}
