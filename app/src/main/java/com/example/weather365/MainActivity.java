package com.example.weather365;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.vPPerDayWeather) ViewPager vPPerDayWeather;

    private DailyWeatherFragmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        HelperFunctions.setApplicationTimeFormat(getApplicationContext());

        adapter = new DailyWeatherFragmentAdapter(getSupportFragmentManager());
        vPPerDayWeather.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
