package cf.yuanbing.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class AnalyzeData {
    public static final AnalyzeData analyzeData = new AnalyzeData();
    private AnalyzeData() {}

    public boolean startAnalyze(String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.getInt("code") == 200) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                LocationWeather.aqi = jsonObject1.getString("aqi");
                LocationWeather.city = jsonObject1.getString("city");
                LocationWeather.notice = jsonObject1.getString("ganmao");
                LocationWeather.temperature = jsonObject1.getString("wendu") + "℃";
                JSONObject jsonObject2 = jsonObject1.getJSONObject("yesterday");
                HashMap<String, String> yesterday = LocationWeather.yesterday;
                yesterday.put("date", jsonObject2.getString("date"));
                yesterday.put("high", jsonObject2.getString("high"));
                yesterday.put("fx", jsonObject2.getString("fx"));
                yesterday.put("low", jsonObject2.getString("low"));
                yesterday.put("fl", jsonObject2.getString("fl"));
                yesterday.put("type", jsonObject2.getString("type"));
                JSONArray jsonArray = jsonObject1.getJSONArray("forecast");
                for (int i = 0; i < jsonArray.length(); i++) {
                    LocationWeather.forecast.add(new HashMap());
                    HashMap forecastDay = LocationWeather.forecast.get(LocationWeather.forecast.size() - 1);
                    JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                    forecastDay.put("date", jsonObject3.getString("date"));
                    forecastDay.put("high", jsonObject3.getString("high"));
                    forecastDay.put("fengli", jsonObject3.getString("fengli"));
                    forecastDay.put("fengxiang", jsonObject3.getString("fengxiang"));
                    forecastDay.put("type", jsonObject3.getString("type"));
                    forecastDay.put("low", jsonObject3.getString("low"));
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
