package com.homecredit.takehomeexam.data;

import com.homecredit.takehomeexam.model.Weather;

public class WeatherSingleton {

    private static WeatherSingleton INSTANCE = null;

    private Weather weather;

    public static WeatherSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WeatherSingleton();
        }
        return INSTANCE;
    }

    public void setWeather(Weather weather){
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
