package cf.yuanbing.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private ArrayList forecastDays;

    public MyRecyclerViewAdapter(ArrayList forecastDays) {
        this.forecastDays = forecastDays;
    }
    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.forecast, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        if (i > 0) {
            myViewHolder.date.setText((String) ((HashMap) forecastDays.get(i - 1)).get("date"));
            myViewHolder.high.setText(((String) ((HashMap) forecastDays.get(i - 1)).get("high")).split(" ")[1]);
            myViewHolder.low.setText("/" + ((String) ((HashMap) forecastDays.get(i - 1)).get("low")).split(" ")[1]);
            myViewHolder.forecastType.setText((String) Objects.requireNonNull(((HashMap) forecastDays.get(i - 1)).get("type")));
            myViewHolder.fx.setText((String) Objects.requireNonNull(((HashMap) forecastDays.get(i - 1)).get("fengxiang")));
            myViewHolder.fl.setText((String) Objects.requireNonNull(((HashMap) forecastDays.get(i - 1)).get("fengli")));
            switch ((String) Objects.requireNonNull(((HashMap) forecastDays.get(i - 1)).get("type"))) {
                case "小雨":
                    myViewHolder.type.setImageResource(R.drawable.xiao_yu);
                    break;
                case "阵雨":
                    myViewHolder.type.setImageResource(R.drawable.zhen_yu);
                    break;
                case "晴":
                    myViewHolder.type.setImageResource(R.drawable.qing);
                    break;
                case "多云":
                    myViewHolder.type.setImageResource(R.drawable.duo_yun);
                    break;
                case "小雪":
                    myViewHolder.type.setImageResource(R.drawable.xiao_xue);
                    break;
                case "冰雹":
                    myViewHolder.type.setImageResource(R.drawable.bing_bao);
                    break;
                case "阴":
                    myViewHolder.type.setImageResource(R.drawable.yin);
                    break;
                case "雾霾":
                    myViewHolder.type.setImageResource(R.drawable.wu_mai);
                    break;
            }
        } else {
            myViewHolder.date.setText(LocationWeather.yesterday.get("date"));
            myViewHolder.high.setText(LocationWeather.yesterday.get("high").split(" ")[1]);
            myViewHolder.low.setText("/" + LocationWeather.yesterday.get("low").split(" ")[1]);
            myViewHolder.forecastType.setText(LocationWeather.yesterday.get("type"));
            myViewHolder.fx.setText(LocationWeather.yesterday.get("fx"));
            myViewHolder.fl.setText(LocationWeather.yesterday.get("fl"));
            switch (LocationWeather.yesterday.get("type")) {
                case "小雨":
                    myViewHolder.type.setImageResource(R.drawable.xiao_yu);
                    break;
                case "阵雨":
                    myViewHolder.type.setImageResource(R.drawable.zhen_yu);
                    break;
                case "晴":
                    myViewHolder.type.setImageResource(R.drawable.qing);
                    break;
                case "多云":
                    myViewHolder.type.setImageResource(R.drawable.duo_yun);
                    break;
                case "小雪":
                    myViewHolder.type.setImageResource(R.drawable.xiao_xue);
                    break;
                case "冰雹":
                    myViewHolder.type.setImageResource(R.drawable.bing_bao);
                    break;
                case "阴":
                    myViewHolder.type.setImageResource(R.drawable.yin);
                    break;
                case "雾霾":
                    myViewHolder.type.setImageResource(R.drawable.wu_mai);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return forecastDays.size() + 1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView type;
        private TextView date;
        private TextView high;
        private TextView low;
        private TextView fx;
        private TextView forecastType;
        private TextView fl;
        public MyViewHolder(View view) {
            super(view);
            type = view.findViewById(R.id.iv_type);
            date = view.findViewById(R.id.tv_forecast_date);
            high = view.findViewById(R.id.tv_forecast_high);
            low = view.findViewById(R.id.tv_forecast_low);
            fl = view.findViewById(R.id.tv_fl);
            fx = view.findViewById(R.id.tv_fx);
            forecastType = view.findViewById(R.id.tv_forecast_type);
        }
    }
}
