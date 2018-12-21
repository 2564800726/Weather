package cf.yuanbing.weather;

import android.os.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class GetData extends Thread {
    private String locate = null;
    public GetData(String locate) {
        this.locate = locate;
    }
    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            locate = URLEncoder.encode(locate, String.valueOf(StandardCharsets.UTF_8));
            final String port = "https://www.apiopen.top/weatherApi?city=" + locate;
            URL url = new URL(port);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = null;
            StringBuilder stringBuffer = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            httpURLConnection.disconnect();
            LocationWeather.clearData();
            startAnalyze(stringBuffer.toString());
            Message message = new Message();
            message.what = 0;
            MainActivity.handler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private boolean startAnalyze(String data) {
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
                String fl = (jsonObject2.getString("fl")).split("\\[")[2].split("]")[0];
                yesterday.put("fl", fl);
                yesterday.put("type", jsonObject2.getString("type"));
                JSONArray jsonArray = jsonObject1.getJSONArray("forecast");
                for (int i = 0; i < jsonArray.length(); i++) {
                    LocationWeather.forecast.add(new HashMap());
                    HashMap forecastDay = LocationWeather.forecast.get(LocationWeather.forecast.size() - 1);
                    JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                    forecastDay.put("date", jsonObject3.getString("date"));
                    forecastDay.put("high", jsonObject3.getString("high"));
                    String fengli = jsonObject3.getString("fengli").split("\\[")[2].split("]")[0];
                    forecastDay.put("fengli", fengli);
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
