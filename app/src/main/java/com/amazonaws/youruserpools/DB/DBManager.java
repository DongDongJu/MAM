package com.amazonaws.youruserpools.DB;

/**
 * Created by DongJu on 2016. 6. 14..
 */
public class DBManager {
    public static String[] songs;
    public static String[] idxs;

    public static final String GET_USABLE_SONG_URL = "http://52.79.159.37/viewAllSongList.php";
    public static final String TAG_IDX = "idx";
    public static final String TAG_Song = "songName";
    public static final String TAG_JSON_ARRAY = "result";

    public DBManager(int i){
        songs = new String[i];
        idxs = new String[i];
    }
}
