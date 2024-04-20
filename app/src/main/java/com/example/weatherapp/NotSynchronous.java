package com.example.weatherapp;

import static com.example.weatherapp.MainActivity.list;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NotSynchronous extends AsyncTask<String, Integer, Long> {
    private JSONArray result;
    @Override
    protected Long doInBackground(String... strings) {
        System.out.println(strings[0]);
        double latitude;
        double longitude;
        try {
            URL zippy = new URL("https://api.openweathermap.org/geo/1.0/zip?zip="+strings[0]+",US&appid=94a4a24772780f07c11f163f47de8326");
            URLConnection pippy = zippy.openConnection();
            pippy.connect();
            InputStream ippy = pippy.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ippy));

            String s;
            String i = "";
            while((s = br.readLine()) != null){
                i += s + "\n";
            }
            br.close();
            ippy.close();

            JSONObject zipInfo = new JSONObject(i);
            Log.d("info", zipInfo.toString());
            latitude = zipInfo.getDouble("lat");
            longitude = zipInfo.getDouble("lon");



            String information = "";

            URL weather = new URL("https://api.openweathermap.org/data/2.5/forecast?lat="+latitude+"&lon="+longitude+"&appid=94a4a24772780f07c11f163f47de8326");
            URLConnection info = weather.openConnection();
            info.connect();
            InputStream in = info.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(in));

            String s2;

            while((s2 = br2.readLine()) != null){
                information += s2 + "\n";
            }
            Log.d("information", information);
            br2.close();
            in.close();

            JSONArray weatherInfo = (new JSONObject(information)).getJSONArray("list");
            JSONArray wi = new JSONArray();

            for(int it = 0; it < weatherInfo.length(); it++){
                wi.put(weatherInfo.get(it));
            }

            Log.d("info", wi.toString());
            wi.put(zipInfo);
            result = wi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        ArrayList<Hour> res = new ArrayList<Hour>();
        for(int i = 0; i < result.length()-1; i++){
            try {
                res.add(new Hour(result.getJSONObject(i), result.getJSONObject(result.length()-1)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        list.setAdapter(new CustomAdapter(list.getContext(), R.layout.adapter_layout, res));
        super.onPostExecute(aLong);
    }
}
