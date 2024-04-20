package com.example.weatherapp;

import org.json.JSONObject;

public class Hour {
    JSONObject info;
    double temp;
    double hi;
    double lo;
    String desc;
    String time;
    String location;
    JSONObject zipfo;

    public Hour(JSONObject inf, JSONObject loc){
        info = inf;
        zipfo = loc;
        try{
            temp = kToF(info.getJSONObject("main").getDouble("temp"));
            hi = kToF(info.getJSONObject("main").getDouble("temp_max"));
            lo = kToF(info.getJSONObject("main").getDouble("temp_min"));
            desc = info.getJSONArray("weather").getJSONObject(0).getString("main");
            location = zipfo.getString("name");
            int t = Integer.parseInt(info.getString("dt_txt").substring(11, 13));

            if(t == 0){
                time = "12:00 a.m.";
            }else if(t < 12) {
                time = t + ":00 a.m.";
            } else if(t == 12) {
                time = t + ":00 p.m.";
            }else{
                time = t - 12 + ":00 p.m.";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static double kToF(double k){

        return Math.round(100*((k-273.15)*(9/5)+32))/100.0;
    }
}
