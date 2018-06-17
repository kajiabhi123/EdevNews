package com.example.designmodal.edevnews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.designmodal.edevnews.R;

/**
 * Created by compware on 6/17/2018.
 */

public class NewsFragment extends Fragment
{
    TextView txt_container;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.newsfeed, container, false);
        txt_container = view.findViewById(R.id.catagory);
        Bundle args = getArguments();
        String myValue = args.getString("categoryParam");
        //int index = args.getInt("index", 0);
        txt_container.setText(myValue);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
