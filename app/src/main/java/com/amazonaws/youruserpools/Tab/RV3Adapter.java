package com.amazonaws.youruserpools.Tab;

/**
 * Created by DongJu on 2016. 6. 15..
 */

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.amazonaws.youruserpools.Data.Song;
import com.amazonaws.youruserpools.MediaPlayerActivity;

import java.util.List;

/**
 * Created by grid on 16. 5. 8..
 */
public class RV3Adapter extends RecyclerView.Adapter<RV3Adapter.StateViewHolder> {
    public static List<Song> songs;
    public static class StateViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView songName;
        ImageView songPhoto;
        List<String> songNames;
        StateViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    //when cardview click
                    Integer tag = (Integer)view.getTag();
                    Intent mediaplayerIntent = new Intent(view.getContext(), MediaPlayerActivity.class);
                    mediaplayerIntent.putExtra("idx",songs.get(tag).songName);
                    view.getContext().startActivity(mediaplayerIntent);
                }
            });
            songName = (TextView)itemView.findViewById(R.id.song_name);
            songPhoto = (ImageView)itemView.findViewById(R.id.song_photo);
        }
    }


    RV3Adapter(List<Song> songs){
        this.songs = songs;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public StateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item, viewGroup, false);
        StateViewHolder pvh = new StateViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StateViewHolder stateViewHolder, int i) {
        stateViewHolder.cv.setTag(i);
        stateViewHolder.songName.setText(songs.get(i).songName);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
