package com.homecredit.takehomeexam.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homecredit.takehomeexam.R;
import com.homecredit.takehomeexam.model.Weather;

import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.MyViewHolder> {


    private List<Weather> weatherList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtPlace, txtActualWeather, txtTemp;

        public MyViewHolder(View view){
            super(view);
            txtPlace = (TextView)view.findViewById(R.id.txt_place);
            txtActualWeather = (TextView)view.findViewById(R.id.txt_actual_weather);
            txtTemp = (TextView)view.findViewById(R.id.txt_temp);
        }
    }

    public WeatherListAdapter(List<Weather> list){
        this.weatherList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_weather_details, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.txtPlace.setText(weather.name);
        holder.txtActualWeather.setText(weather.weather.get(0).description);
        holder.txtTemp.setText(weather.main.temp);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
