package com.homecredit.takehomeexam.model;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable {

    public static final String TABLE_NAME = "weather";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_DATA = "data";

    private String place;
    private String data;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PLACE + " TEXT,"
                    + COLUMN_DATA + " TEXT"
                    + ")";

    public Coord coord;
    public List<WeatherData> weather;
    public String base;
    public Main main;
    public String visibility;
    public Wind wind;
    public Clouds clouds;
    public String dt = "";
    public Sys sys;
    public String id = "";
    public String name = "";
    public String cod = "";
}
