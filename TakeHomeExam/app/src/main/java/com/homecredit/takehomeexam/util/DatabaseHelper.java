package com.homecredit.takehomeexam.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.homecredit.takehomeexam.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "weather_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Weather.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Weather.TABLE_NAME);
        onCreate(db);
    }

    public long insertWeather(String place, String data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Weather.COLUMN_PLACE, place);
        values.put(Weather.COLUMN_DATA, data);

        long id = db.insert(Weather.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public List<String> getAllWeather() {
        List<String> weather = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Weather.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                weather.add(cursor.getString(cursor.getColumnIndex(Weather.COLUMN_DATA)));
            } while (cursor.moveToNext());
        }
        db.close();

        return weather;
    }

    public int updateWeather(String place, String data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Weather.COLUMN_DATA, data);

        return db.update(Weather.TABLE_NAME, values, Weather.COLUMN_PLACE + " = ?",
                new String[]{place});
    }

    public boolean isPlaceExisting(String place) {
        boolean isPlaceExisting = false;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Weather.TABLE_NAME,
                new String[]{Weather.COLUMN_ID, Weather.COLUMN_PLACE, Weather.COLUMN_DATA},
                Weather.COLUMN_PLACE + "=?",
                new String[]{place}, null, null, null, null);

        if (cursor != null) {
            isPlaceExisting = cursor.moveToFirst();
        }
        cursor.close();

        return isPlaceExisting;
    }
}
