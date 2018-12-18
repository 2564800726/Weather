package cf.yuanbing.weather;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationWeather {
    public static String city = null;
    public static HashMap<String, String> yesterday = new HashMap<>();
    public static ArrayList<HashMap> forecast = new ArrayList<>();
    public static String temperature = null;
    public static String notice = null;
    public static String aqi = null;

    public static void clearData() {
        city = null;
        yesterday.clear();
        forecast.clear();
        temperature = null;
        notice = null;
        aqi = null;
    }
}
