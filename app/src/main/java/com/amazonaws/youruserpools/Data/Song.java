package com.amazonaws.youruserpools.Data;

/**
 * Created by DongJu on 2016. 6. 14..
 */
public class Song {
    int songId;
    public String songName;
    public Song(int songId , String songName){
        this.songId = songId;
        this.songName = songName;
    }
    public String getName(){
        return this.songName;
    }
}
