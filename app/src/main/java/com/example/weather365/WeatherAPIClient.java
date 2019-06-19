package com.example.weather365;

import com.example.weather365.Models.WeatherAPIResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class WeatherAPIClient {

    private static final String BASE_URL = "https://samples.openweathermap.org/";
    private static final String API_KEY = "9de3651d0ee6b02f03b501f34367b34b";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getApiKey() {
        return API_KEY;
    }


}


interface WeatherAPIInterface{
    @GET("data/2.5/forecast")
    Call<WeatherAPIResponse> getWeatherData(@Query("q") String cityWithCountryCode, @Query("appid") String appid);

}