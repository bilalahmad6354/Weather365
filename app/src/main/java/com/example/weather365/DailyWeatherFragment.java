package com.example.weather365;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather365.Models.WeatherAPIResponse;
import com.example.weather365.Models.WeatherData;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DailyWeatherFragment extends Fragment {

    //Views outlets
    @BindView(R.id.rvPerThreeHours) RecyclerView rvPerThreeHours;
    @BindView(R.id.rvPerDay) RecyclerView rvPerDay;
    @BindView(R.id.tvCityName) TextView tvCityName;
    @BindView(R.id.tvCurrentTime) TextView tvCurrentTime;
    @BindView(R.id.tvMaxTemp) TextView tvMaxTemp;
    @BindView(R.id.tvMinTemp) TextView tvMinTemp;
    @BindView(R.id.tvAvgTemp) TextView tvAvgTemp;
    @BindView(R.id.ivDefaultWeather) ImageView ivDefaultWeather;
    @BindView(R.id.progressBar) AVLoadingIndicatorView progressBar;
    @BindView(R.id.tvDefaultWeatherStatus) TextView tvDefaultWeatherStatus;

    //Activity related variables
    public static final String TAG = "WEATHER";
    public CountDownTimer timer;
    private WeahterPTHAdapter adapterPerThreeHours;
    private WeatherPerDayRecyclerAdapter adapterPerDay;
    protected List<WeatherData> listWeatherData = new ArrayList<>();



    public DailyWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily_weather, container, false);
        ButterKnife.bind(this, view);

        String cityName = getArguments().getString("cityName");
        String countryCode = getArguments().getString("countryCode");
        HelperFunctions.setApplicationTimeFormat(getActivity());
        getWeatherDataForCity(cityName, countryCode);

        tvCityName.setText(cityName);
        timer = getTimerInstance();
        timer.start();
        progressBar.show();
        adapterPerThreeHours = new WeahterPTHAdapter(getActivity(), listWeatherData);
        adapterPerDay = new WeatherPerDayRecyclerAdapter(getActivity(), listWeatherData);
        rvPerDay.setAdapter(adapterPerDay);
        rvPerThreeHours.setAdapter(adapterPerThreeHours);
        rvPerThreeHours.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvPerDay.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvPerDay.addItemDecoration(new DividerItemDecoration(rvPerDay.getContext(), DividerItemDecoration.HORIZONTAL));

        return view;
    }


    public CountDownTimer getTimerInstance(){
        return new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                  tvCurrentTime.setText(HelperFunctions.getTime(getContext(),  System.currentTimeMillis()));
            }
            public void onFinish() {

            }
        };
    }


    public void getWeatherDataForCity(String cityName, String countryCode)
    {
        progressBar.show();
        WeatherAPIInterface apiService =
                WeatherAPIClient.getClient().create(WeatherAPIInterface.class);
        Call<WeatherAPIResponse> call = apiService.getWeatherData(cityName + "," + countryCode, WeatherAPIClient.getApiKey());

        call.enqueue(new Callback<WeatherAPIResponse>() {
            @Override
            public void onResponse(Call<WeatherAPIResponse> call, Response<WeatherAPIResponse> response) {
                WeatherAPIResponse obj = response.body();
                listWeatherData = obj.getList();

                List<WeatherData> dataSourceForPerDayAdapter = getDataSourceForPerDayRecyclerView();
                List<WeatherData> dataSourceForPerThreeHoursAdapter = getDataSourceForPerThreeHoursRecyclerView();

                adapterPerThreeHours = new WeahterPTHAdapter(getActivity(), dataSourceForPerThreeHoursAdapter);
                adapterPerDay = new WeatherPerDayRecyclerAdapter(getActivity(), dataSourceForPerDayAdapter);

                rvPerThreeHours.setAdapter(adapterPerThreeHours);
                rvPerDay.setAdapter(adapterPerDay);

                adapterPerDay.notifyDataSetChanged();
                adapterPerThreeHours.notifyDataSetChanged();

                populateData(obj);
                progressBar.hide();
            }

            @Override
            public void onFailure(Call<WeatherAPIResponse> call, Throwable t) {
                try {
                    Toast.makeText(getActivity(), getString(R.string.text_error_longer_task), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), getString(R.string.text_trying_again), Toast.LENGTH_SHORT).show();
                    getWeatherDataForCity(cityName, countryCode);
                }catch(IllegalStateException ex){

                }
                catch (Exception ex){

                }
            }
        });
    }


    public List<WeatherData> getDataSourceForPerDayRecyclerView(){

        List<WeatherData> dataSourceForPerDayAdapter = new ArrayList<>();
        List<String> bufferForDays = new ArrayList<>();

        for(int i = 0 ; i < listWeatherData.size() ; i++){
            String temp = HelperFunctions.getWeekDay(Long.parseLong(listWeatherData.get(i).getDt()));
            if(bufferForDays.indexOf(temp) < 0){
                dataSourceForPerDayAdapter.add(listWeatherData.get(i));
                bufferForDays.add(temp);
            }
        }
        return  dataSourceForPerDayAdapter;
    }

    public List<WeatherData> getDataSourceForPerThreeHoursRecyclerView(){

        List<WeatherData> dataSourceForPerThreeHoursRecyclerView = new ArrayList<>();List<String> bufferForDays = new ArrayList<>();

        if(listWeatherData.size() > 7) {
            for (int i = 0; i < 8; i++) {
                dataSourceForPerThreeHoursRecyclerView.add(listWeatherData.get(i));
            }
        }
        return  dataSourceForPerThreeHoursRecyclerView;
    }


    public void populateData(WeatherAPIResponse obj){

        try {
            tvMaxTemp.setText(HelperFunctions.fehrenToCelcius(obj.getList().get(0).getMain().getTemp_max()) + getString(R.string.text_dummy_temperature));
            tvMinTemp.setText(HelperFunctions.fehrenToCelcius(obj.getList().get(0).getMain().getTemp_min()) + getString(R.string.text_dummy_temperature));
            tvAvgTemp.setText(HelperFunctions.fehrenToCelcius(obj.getList().get(0).getMain().getTemp()) + getString(R.string.text_dummy_temperature));
            tvDefaultWeatherStatus.setText(obj.getList().get(0).getWeather().get(0).getDescription().substring(0, 1).toUpperCase()
                    + obj.getList().get(0).getWeather().get(0).getDescription().substring(1));
            String a = "http://openweathermap.org/img/w/" + obj.getList().get(0).getWeather().get(0).getIcon() + ".png";
            Picasso.with(getActivity()).load("http://openweathermap.org/img/w/" + obj.getList().get(0).getWeather().get(0).getIcon() + ".png").placeholder(R.drawable.icon_default_weather).into(ivDefaultWeather);
        }catch (Exception ex){

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
