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
        myViewHolder.date.setText((String) ((HashMap) forecastDays.get(i)).get("date"));
        myViewHolder.high.setText(((String) ((HashMap) forecastDays.get(i)).get("high")).split(" ")[1]);
        myViewHolder.low.setText("/" + ((String) ((HashMap) forecastDays.get(i)).get("low")).split(" ")[1]);
        switch ((String) Objects.requireNonNull(((HashMap) forecastDays.get(i)).get("type"))) {
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

    @Override
    public int getItemCount() {
        return forecastDays.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView type;
        private TextView date;
        private TextView high;
        private TextView low;
        public MyViewHolder(View view) {
            super(view);
            type = view.findViewById(R.id.iv_type);
            date = view.findViewById(R.id.tv_forecast_date);
            high = view.findViewById(R.id.tv_forecast_high);
            low = view.findViewById(R.id.tv_forecast_low);
        }
    }
}
