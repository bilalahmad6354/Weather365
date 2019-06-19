package com.example.weather365.Models;

import com.google.gson.annotations.SerializedName;

public class Coordinates{
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;

    public Coordinates(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


}
