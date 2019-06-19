package com.example.weather365.Models;

import com.google.gson.annotations.SerializedName;

public class City{

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private Coordinates coord;
    @SerializedName("country")
    private String country;

    public City(String id, String name, Coordinates coord, String country) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
