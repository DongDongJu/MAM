package com.amazonaws.youruserpools;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.amazonaws.youruserpools.DB.DBManager;
import com.amazonaws.youruserpools.Data.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MediaPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private String Idx;
    private static final String VIDEO_PATH = "http://d101b4z8snf4ko.cloudfront.net/output/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        Intent temp = getIntent();
        Idx = temp.getStringExtra("idx");
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(MediaPlayerActivity.this);
        Button addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSong();
                Toast.makeText(view.getContext(),"add success",Toast.LENGTH_SHORT);
            }
        });
        Button deleteButton = (Button)findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delSong();
                Toast.makeText(view.getContext(),"delete success",Toast.LENGTH_SHORT);
            }
        });
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDisplay(mSurfaceHolder);
        try {
            System.out.println(VIDEO_PATH+Idx+".m3u8");
            mMediaPlayer.setDataSource(VIDEO_PATH+Idx+".m3u8");
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(MediaPlayerActivity.this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    private void addSong(){
        class AddData extends AsyncTask<Void,Void,String> {
            //ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressDialog = ProgressDialog.show(, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressDialog.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    System.out.println("http://52.79.159.37/addSongSongList.php?userName="+MainActivity.username+"&songName="+Idx);
                    URL url = new URL("http://52.79.159.37/addSongSongList.php?userName="+MainActivity.username+"&songName="+Idx);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String line = null;
                    while((line = bufferedReader.readLine())!=null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();

                }catch(Exception e){
                    return null;
                }
            }
        }
        AddData gd = new AddData();
        gd.execute();
    }
    private void delSong(){
        class delData extends AsyncTask<Void,Void,String> {
            //ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressDialog = ProgressDialog.show(, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //progressDialog.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    System.out.println("http://52.79.159.37/delSongSongList.php?userName="+MainActivity.username+"&songName="+Idx);
                    URL url = new URL("http://52.79.159.37/delSongSongList.php?userName="+MainActivity.username+"&songName="+Idx);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String line = null;
                    while((line = bufferedReader.readLine())!=null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();

                }catch(Exception e){
                    return null;
                }
            }
        }
        delData gd = new delData();
        gd.execute();
    }
}