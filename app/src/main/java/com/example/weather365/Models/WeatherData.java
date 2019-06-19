package com.example.weather365.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData{

    @SerializedName("dt")
    private String dt;
    @SerializedName("main")
    private MainData main;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("clouds")
    private Cloud clouds;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("dt_txt")
    private String dt_txt;

    public WeatherData(String dt, MainData main, List<Weather> weather, Cloud clouds, Wind wind, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.dt_txt = dt_txt;
    }
    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public MainData getMain() {
        return main;
    }

    public void setMain(MainData main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }



}
