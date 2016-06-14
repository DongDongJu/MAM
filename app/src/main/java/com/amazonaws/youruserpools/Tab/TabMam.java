package com.amazonaws.youruserpools.Tab;

/**
 * Created by DongJu on 2016. 6. 14..
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;
import com.amazonaws.youruserpools.DB.DBManager;
import com.amazonaws.youruserpools.DB.DBManager2;

public class TabMam extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_mam,container,false);
        Button mamButton = (Button)v.findViewById(R.id.saveButton);
        mamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return v;
    }
}