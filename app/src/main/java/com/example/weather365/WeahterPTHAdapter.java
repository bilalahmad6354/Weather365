package com.example.weather365;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weather365.Models.TimeFormat;
import com.example.weather365.Models.WeatherData;
import com.squareup.picasso.Picasso;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class WeahterPTHAdapter extends RecyclerView.Adapter<WeahterPTHAdapter.HolderForPerThreeHoursView> {

    // Adapter related variables
    private LayoutInflater inflater;
    private final String TAG = this.getClass().getSimpleName();
    private List<WeatherData> weatherData;
    private String outputDateFormatFor24HoursFormat = "HH:mm";
    private String outputDateFormatFor12HoursFormat = "hh:mm a";

    public WeahterPTHAdapter(Context ctx, List<WeatherData> data) {
        try {
            inflater = LayoutInflater.from(ctx);
        }catch (Exception ex){
           //Accessing Illegal Resources
        }

        this.weatherData = data;
    }

    @Override
    public WeahterPTHAdapter.HolderForPerThreeHoursView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.weather_per_three_hours_card, parent, false);
        HolderForPerThreeHoursView holder = new HolderForPerThreeHoursView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WeahterPTHAdapter.HolderForPerThreeHoursView holder, int position) {

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat outputFormat;

        if(GlobalVariables.SYSTEM_TIME_FORMAT == TimeFormat.TWELVE_HOURS){
            outputFormat = new SimpleDateFormat(outputDateFormatFor12HoursFormat);
        }else if (GlobalVariables.SYSTEM_TIME_FORMAT == TimeFormat.TWENTY_FOUR_HOURS){
            outputFormat = new SimpleDateFormat(outputDateFormatFor24HoursFormat);
        }else{
            // Default case
            outputFormat = new SimpleDateFormat(outputDateFormatFor12HoursFormat);
        }

        Context context = holder.ivWeatherIcon.getContext();
        Picasso.with(context).load("http://openweathermap.org/img/w/" + weatherData.get(position).getWeather().get(0).getIcon() + ".png").placeholder(R.drawable.icon_default_weather).into(holder.ivWeatherIcon);
        try {
            holder.tvTime.setText(outputFormat.format(inputFormat.parse(weatherData.get(position).getDt_txt())).toUpperCase());
        }catch (ParseException ex){

        }
        holder.tvTemperature.setText(HelperFunctions.fehrenToCelcius(weatherData.get(position).getMain().getTemp()) + context.getString(R.string.text_dummy_temperature) );
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }
    class HolderForPerThreeHoursView extends RecyclerView.ViewHolder{

        TextView tvTime;
        ImageView ivWeatherIcon;
        TextView tvTemperature;

        public HolderForPerThreeHoursView(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            ivWeatherIcon = (ImageView) itemView.findViewById(R.id.ivWeatherIcon);
            tvTemperature = (TextView) itemView.findViewById(R.id.tvTemperature);
        }

    }
}
