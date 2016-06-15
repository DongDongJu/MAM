package com.amazonaws.youruserpools.Tab;

/**
 * Created by DongJu on 2016. 6. 14..
 */
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.amazonaws.youruserpools.DB.DBManager2;
import com.amazonaws.youruserpools.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TabMam extends Fragment {
    public static Integer selNum;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_mam,container,false);
        Button mamButton = (Button)v.findViewById(R.id.saveButton);
        final EditText edit = (EditText)v.findViewById(R.id.placeText);
        mamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selNum = Integer.parseInt(edit.getText().toString());
                System.out.println(selNum);
                MAM();
            }
        });
        return v;
    }
    private void MAM(){
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
                System.out.println(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                BufferedReader bufferedReader = null;
                try {
                    System.out.println("http://52.79.159.37/MAM.php?times="+ selNum.toString()+"&userName="+MainActivity.username);
                    URL url = new URL("http://52.79.159.37/MAM.php?times="+ selNum.toString()+"&userName="+MainActivity.username);
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
}