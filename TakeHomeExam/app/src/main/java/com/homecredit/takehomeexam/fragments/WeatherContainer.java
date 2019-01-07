package com.homecredit.takehomeexam.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homecredit.takehomeexam.R;

public class WeatherContainer extends BaseContainerFragment{

    private boolean hasInstance = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_container, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!hasInstance){
            hasInstance = true;
            replaceFragment(new WeatherList(), false);
        }
    }
}
