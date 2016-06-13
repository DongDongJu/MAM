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
import com.amazonaws.youruserpools.CognitoYourUserPoolsDemo.R;

public class TabSongList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_songlist,container,false);
        return v;
    }
}