package com.amazonaws.youruserpools.DB;

import com.amazonaws.youruserpools.Data.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DongJu on 2016. 6. 14..
 */
public class DBManager2 {
    public static String[] songs;
    public static String[] idxs;
    public static List<Song> selectedSong;
    public static final String GET_USABLE_SONG_URL = "http://52.79.159.37/viewAllSongList.php";
    public static final String TAG_IDX = "idx";
    public static final String TAG_Song = "songName";
    public static final String TAG_JSON_ARRAY = "result";
    public static Integer Num;
    public DBManager2(int i){
        songs = new String[i];
        idxs = new String[i];
        selectedSong = new ArrayList<>();
        selectedSong.add(new Song(0,"----------"));
    }
}
