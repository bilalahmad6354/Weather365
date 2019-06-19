package com.example.weather365;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather365.Models.WeatherData;
import com.squareup.picasso.Picasso;
import java.util.List;

public class WeatherPerDayRecyclerAdapter extends  RecyclerView.Adapter<WeatherPerDayRecyclerAdapter.HolderForPerDayView>{

    private LayoutInflater inflater;
    private List<WeatherData> weatherData;
    final String TAG = this.getClass().getSimpleName();
    private String iconsExtension = ".png";
    private String baseURLForIcons = "http://openweathermap.org/img/w/";

    public WeatherPerDayRecyclerAdapter(Context ctx, List<WeatherData> data) {
        try {
            inflater = LayoutInflater.from(ctx);
        }catch (Exception ex){
            //Accessing illegal Resources
        }
        this.weatherData = data;
    }

    @Override
    public HolderForPerDayView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.weather_per_day_row, parent, false);
        WeatherPerDayRecyclerAdapter.HolderForPerDayView holder = new WeatherPerDayRecyclerAdapter.HolderForPerDayView(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(WeatherPerDayRecyclerAdapter.HolderForPerDayView holder, int position) {

        Context context = holder.ivWeatherIcon.getContext();
        Picasso.with(context).load(baseURLForIcons + weatherData.get(position).getWeather().get(0).getIcon() + iconsExtension).placeholder(R.drawable.icon_default_weather).into(holder.ivWeatherIcon);
        holder.tvMinTemp.setText(HelperFunctions.fehrenToCelcius(weatherData.get(position).getMain().getTemp_min()) + context.getString(R.string.text_dummy_temperature) );
        holder.tvMaxTemp.setText(HelperFunctions.fehrenToCelcius(weatherData.get(position).getMain().getTemp_max()) + context.getString(R.string.text_dummy_temperature) );
        holder.tvDayName.setText(HelperFunctions.getWeekDay(Long.parseLong(weatherData.get(position).getDt())));
    }


    @Override
    public int getItemCount() {
        return weatherData.size();
    }


    class HolderForPerDayView extends RecyclerView.ViewHolder{

        TextView tvMaxTemp;
        ImageView ivWeatherIcon;
        TextView tvMinTemp;
        TextView tvDayName;

        public HolderForPerDayView(View itemView) {
            super(itemView);
            tvMaxTemp = (TextView) itemView.findViewById(R.id.tvMaxTemp);
            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.ivWeatherIcon);
            tvMinTemp = (TextView) itemView.findViewById(R.id.tvMinTemp);
            tvDayName = (TextView) itemView.findViewById(R.id.tvDay);
        }

    }
}
