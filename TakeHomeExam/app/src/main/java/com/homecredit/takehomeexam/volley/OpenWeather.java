package com.homecredit.takehomeexam.volley;


import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.homecredit.takehomeexam.volleyinterface.OpenWeatherInterface;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeather {

    public OpenWeatherInterface openWeatherInterface;

    public void getWeather(Context context, String place){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL(place), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    openWeatherInterface.result(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                openWeatherInterface.error(parseVolleyError(error));
            }
        });

        Volley.newRequestQueue(context).add(stringRequest);
    }

    private String URL(String place){
        return "http://api.openweathermap.org/data/2.5/weather?q="+place+"&APPID=7ded57f3f1a4c5d7709a2845170a21d9";
    }

    private String parseVolleyError(VolleyError error){
        String desc = "";

        if(error == null){
            return "Server Timeout";
        }
        if (error instanceof NoConnectionError) {
            desc = "No internet connection";
        }
        if (error instanceof TimeoutError) {
            desc = "Server Timeout";
        }
        if (error.networkResponse != null && error.networkResponse.data != null) {
            desc = "Error encountered";
        }

        return desc;
    }
}
