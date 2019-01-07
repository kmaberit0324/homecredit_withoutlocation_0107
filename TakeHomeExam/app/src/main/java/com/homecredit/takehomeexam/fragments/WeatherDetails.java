package com.homecredit.takehomeexam.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.homecredit.takehomeexam.R;
import com.homecredit.takehomeexam.model.Weather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeatherDetails extends Fragment{

    private ImageView ivImage;
    private TextView txtSpeed, txtDegree, txtCloudiness, txtPressure, txtHumidity, txtSunRise, txtSunSet, txtCoordinates;

    private String WEATHER_TAG = "weather";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);
        initObjects(view);

        Bundle bundle = getArguments();
        Weather weather = (Weather) bundle.getSerializable(WEATHER_TAG);

        Picasso.with(getContext()).load(weatherIconUrl(weather.weather.get(0).icon)).resize(500, 500).into(ivImage);
        txtSpeed.setText("Speed: " + weather.wind.speed + " m/s");
        txtDegree.setText("Degree: " + weather.wind.deg);
        txtCloudiness.setText("Cloudiness: " + weather.weather.get(0).description);
        txtPressure.setText("Pressure: " + weather.main.pressure + " hpa");
        txtHumidity.setText("Humidity: " + weather.main.humidity + "%");
        txtCoordinates.setText("Geo Coordinates: " + weather.coord.lat + "," + weather.coord.lon);
        return view;
    }

    private String weatherIconUrl(String id){
        return "http://openweathermap.org/img/w/"+id+".png";
    }

    private void initObjects(View view) {
        ivImage = view.findViewById(R.id.iv_image);
        txtSpeed = view.findViewById(R.id.txt_speed);
        txtDegree = view.findViewById(R.id.txt_degree);
        txtCloudiness = view.findViewById(R.id.txt_cloudiness);
        txtPressure = view.findViewById(R.id.txt_pressure);
        txtHumidity = view.findViewById(R.id.txt_humidity);
        txtSunRise = view.findViewById(R.id.txt_sun_rise);
        txtSunSet = view.findViewById(R.id.txt_sun_set);
        txtCoordinates = view.findViewById(R.id.txt_coordinates);
    }

}
