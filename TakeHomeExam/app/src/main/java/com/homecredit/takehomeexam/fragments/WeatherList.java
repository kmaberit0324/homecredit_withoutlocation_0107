package com.homecredit.takehomeexam.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.homecredit.takehomeexam.R;
import com.homecredit.takehomeexam.adapter.WeatherListAdapter;
import com.homecredit.takehomeexam.model.Weather;
import com.homecredit.takehomeexam.util.ClickListener;
import com.homecredit.takehomeexam.util.DatabaseHelper;
import com.homecredit.takehomeexam.util.RecyclerViewClickListener;
import com.homecredit.takehomeexam.volley.OpenWeather;
import com.homecredit.takehomeexam.volleyinterface.OpenWeatherInterface;

import java.util.ArrayList;
import java.util.List;

public class WeatherList extends Fragment implements OpenWeatherInterface {

    private RecyclerView recyclerView;
    private List<Weather> weatherList = new ArrayList<>();
    private WeatherListAdapter weatherListAdapter = new WeatherListAdapter(weatherList);;

    private String WEATHER_TAG = "weather";

    private DatabaseHelper db;
    private Gson gson = new Gson();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);

        recyclerView = view.findViewById(R.id.list);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        weatherList.clear();
        populateList();
        setRecyclerView();

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void OnClick(View view, int position) {
                ((BaseContainerFragment) getParentFragment()).replaceFragment(weatherDetailsFragment(weatherList.get(position)), true);
            }
        }));
    }

    private Fragment weatherDetailsFragment(Weather weather){
        WeatherDetails weatherDetails = new WeatherDetails();
        Bundle bundle = new Bundle();
        bundle.putSerializable(WEATHER_TAG, weather);
        weatherDetails.setArguments(bundle);
        return weatherDetails;
    }

    private void populateList() {
        if(isOnline()){
            Toast.makeText(getContext(), "Online Mode", Toast.LENGTH_LONG).show();
            connectToOpenWeatherApi();
        } else {
            Toast.makeText(getContext(), "Offline Mode", Toast.LENGTH_LONG).show();
            getDataFromDb();
        }
    }

    private void getDataFromDb() {
        db = new DatabaseHelper(getContext());
        List<String> list = db.getAllWeather();
        if(list.size() > 0)
        {
            for (String str : list){
                weatherList.add(parseWeather(str));
            }
            weatherListAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "Zero data collected", Toast.LENGTH_LONG).show();
        }
    }

    private void connectToOpenWeatherApi() {
        OpenWeather openWeather = openWeatherVolley();
        openWeather.getWeather(getActivity(), "London,UK");
        openWeather.getWeather(getActivity(), "Prague");
        openWeather.getWeather(getActivity(), "San Francisco,US");
    }

    @NonNull
    private OpenWeather openWeatherVolley() {
        OpenWeather openWeather = new OpenWeather();
        openWeather.openWeatherInterface = this;
        return openWeather;
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(weatherListAdapter);
    }

    private Weather parseWeather(String response) {
        return gson.fromJson(response, Weather.class);
    }

    private void saveInDb(String response) {
        Weather weather = gson.fromJson(response, Weather.class);
        db = new DatabaseHelper(getContext());
        if(db.isPlaceExisting(weather.name)){
            db.updateWeather(weather.name, response);
        } else {
            db.insertWeather(weather.name, response);
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void result(String response) {
        weatherList.add(parseWeather(response));
        saveInDb(response);
        weatherListAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), "Error encountered", Toast.LENGTH_LONG).show();
    }


}
