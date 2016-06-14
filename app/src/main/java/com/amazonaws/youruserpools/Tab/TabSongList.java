package com.amazonaws.youruserpools.Tab;

/**
 * Created by DongJu on 2016. 6. 14..
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.amazonaws.youruserpools.DB.DBManager;
import com.amazonaws.youruserpools.Data.Song;
import com.amazonaws.youruserpools.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TabSongList extends Fragment {
    private List<Song> songs;
    private RecyclerView rv;
    private DBManager config;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_songlist,container,false);
        rv = (RecyclerView)v.findViewById(R.id.rv);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        getData();
        return v;
    }
    private void initializeData(){
        songs = new ArrayList<>();
        for(int i=0; i< config.songs.length;i++)
        {
            songs.add(new Song(R.drawable.music,config.songs[i]));
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(songs);
        rv.setAdapter(adapter);
    }
    private void getData(){
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(getContext(), "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                parseJSON(s);
                initializeData();
                initializeAdapter();

            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL("http://52.79.159.37/viewAllSongList.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }
    private void parseJSON(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray(DBManager.TAG_JSON_ARRAY);

            config = new DBManager(array.length());

            for(int i=0; i<array.length(); i++){
                JSONObject j = array.getJSONObject(i);
                DBManager.songs[i] = getName(j);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String getName(JSONObject j){
        String name = null;
        try {
            name = j.getString(DBManager.TAG_Song);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }
}