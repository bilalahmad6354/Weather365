package com.example.weather365.Models;

import com.google.gson.annotations.SerializedName;

public class MainData{
    @SerializedName("temp")
    private Double temp;
    @SerializedName("temp_min")
    private Double temp_min;
    @SerializedName("temp_max")
    private Double temp_max;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("sea_level")
    private String sea_level;
    @SerializedName("grnd_level")
    private String grnd_level;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("temp_kf")
    private String temp_kf;

    @SerializedName("rain")
    private Rain rain;
    @SerializedName("snow;")
    private Snow snow;


    public MainData(Double temp, Double temp_min, Double temp_max, String pressure, String sea_level, String grnd_level, String humidity, String temp_kf, Rain rain, Snow snow) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
        this.humidity = humidity;
        this.temp_kf = temp_kf;
        this.rain = rain;
        this.snow = snow;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSea_level() {
        return sea_level;
    }

    public void setSea_level(String sea_level) {
        this.sea_level = sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(String grnd_level) {
        this.grnd_level = grnd_level;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(String temp_kf) {
        this.temp_kf = temp_kf;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

}
