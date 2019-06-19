package com.example.weather365;

import android.content.Context;
import android.text.format.DateFormat;

import com.example.weather365.Models.TimeFormat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HelperFunctions {

    public static String fehrenToCelcius(Double temp) {
        DecimalFormat df2 = new DecimalFormat("#.#");
        temp = ((temp - 32) * 5) / 9 - 100;
        return df2.format(temp);
    }

    public static String getWeekDay(long unixts) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date dateFormat = new java.util.Date(unixts * 1000);
        String weekday = sdf.format(dateFormat);
        return weekday;
    }

    public static void setApplicationTimeFormat(Context ctx) {
        if (DateFormat.is24HourFormat(ctx)) {
            GlobalVariables.SYSTEM_TIME_FORMAT = TimeFormat.TWENTY_FOUR_HOURS;
        } else {
            GlobalVariables.SYSTEM_TIME_FORMAT = TimeFormat.TWELVE_HOURS;
        }
    }


    public static String getTime(Context context, long time) {
        if (android.text.format.DateFormat.is24HourFormat(context)) {
            return new SimpleDateFormat("HH:mm", Locale.US).format(new Date(time));

        } else {
            return new SimpleDateFormat("hh:mm a", Locale.US).format(new Date(time));
        }
    }
}
