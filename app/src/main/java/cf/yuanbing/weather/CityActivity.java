package cf.yuanbing.weather;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);
        RecyclerView recyclerView = findViewById(R.id.rv_forecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(LocationWeather.forecast));
        Toolbar toolbar = findViewById(R.id.tb_city);
        toolbar.setTitle(LocationWeather.city);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationWeather.clearData();
                finish();
            }
        });
        TextView notice = findViewById(R.id.tv_notice);
        notice.setText(LocationWeather.notice);

        TextView temperature = findViewById(R.id.tv_temperature);
        temperature.setText(LocationWeather.temperature);

        TextView high = findViewById(R.id.tv_high);
        high.setText(((String) LocationWeather.forecast.get(0).get("high")).split(" ")[1]);

        TextView low = findViewById(R.id.tv_low);
        low.setText(((String) LocationWeather.forecast.get(0).get("low")).split(" ")[1]);

        String weather = (String) LocationWeather.forecast.get(0).get("type");
        TextView type = findViewById(R.id.tv_type);
        type.setText(weather);
        ConstraintLayout constraintLayout = findViewById(R.id.cl_city);
        switch (weather) {
            case "晴":
            case "多云":
                constraintLayout.setBackgroundResource(R.drawable.qing_background);
                break;
            case "阴":
            case "雾霾":
                constraintLayout.setBackgroundResource(R.drawable.yin_background);
                break;
            case "小雨":
            case "大雨":
            case "中雨":
            case "暴雨":
                constraintLayout.setBackgroundResource(R.drawable.yu_tian_background);
                break;
            case "小雪":
            case "大雪":
            case "暴雪":
                constraintLayout.setBackgroundResource(R.drawable.xue_background);
                break;
        }
    }
}
