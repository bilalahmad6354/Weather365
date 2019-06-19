package com.example.weather365.Models;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


public class WeatherAPIResponse {


    @SerializedName("cod")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("city")
    private City city;
    @SerializedName("cnt")
    private String cnt;
    @SerializedName("list")
    private List<WeatherData> list;

    public WeatherAPIResponse(String code, String message, City city, String cnt, List<WeatherData> list) {
        this.code = code;
        this.message = message;
        this.city = city;
        this.cnt = cnt;
        this.list = list;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }



    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public City getCity() {
        return city;
    }

    public String getCnt() {
        return cnt;
    }

    public List<WeatherData> getList() {
        return list;
    }

}

class Cloud{
    @SerializedName("all")
    private String all;

    public Cloud(String all) {
        this.all = all;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

}

class Wind{
    @SerializedName("speed")
    private String speed;
    @SerializedName("deg")
    private String deg;

    public Wind(String speed, String deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }



}
class Rain{
    @SerializedName("3h")
    private String perThreeHourse;

    public Rain(String perThreeHourse) {
        this.perThreeHourse = perThreeHourse;
    }

    public String getPerThreeHourse() {
        return perThreeHourse;
    }

    public void setPerThreeHourse(String perThreeHourse) {
        this.perThreeHourse = perThreeHourse;
    }



}

class Snow{
    @SerializedName("3h")
    private String perThreeHours;
    public Snow(String perThreeHours) {
        this.perThreeHours = perThreeHours;
    }

    public String getPerThreeHours() {
        return perThreeHours;
    }

    public void setPerThreeHours(String perThreeHours) {
        this.perThreeHours = perThreeHours;
    }



}

