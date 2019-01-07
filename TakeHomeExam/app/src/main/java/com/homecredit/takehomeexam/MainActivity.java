package com.homecredit.takehomeexam;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.homecredit.takehomeexam.data.WeatherSingleton;
import com.homecredit.takehomeexam.fragments.BaseContainerFragment;
import com.homecredit.takehomeexam.fragments.WeatherContainer;
import com.homecredit.takehomeexam.fragments.WeatherList;
import com.homecredit.takehomeexam.model.Weather;
import com.homecredit.takehomeexam.volley.OpenWeather;
import com.homecredit.takehomeexam.volleyinterface.OpenWeatherInterface;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private String WEATHER_CONTAINER_TAG = "container";

    //private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, new WeatherContainer(), WEATHER_CONTAINER_TAG);
        fragmentTransaction.commit();

//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        fusedLocationProviderClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            Toast.makeText(getApplicationContext(),
//                                    "Get Last Location: " + location.getLatitude() + "," + location.getLongitude(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentByTag(WEATHER_CONTAINER_TAG);
        if(!((BaseContainerFragment)fragment).popFragment()){
            super.onBackPressed();
        }
    }
}
