package com.example.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.weatherapp.MainActivity;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Hour> implements Serializable {

    List list;
    Context context;
    int xmlRes;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Hour> objects) {
        super(context, resource, objects);
        xmlRes = resource;
        list = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View adapterLayout = layoutInflater.inflate(xmlRes, null);

        Hour item = (Hour) list.get(position);
//deg hi lo desc quote
        ((TextView)(adapterLayout.findViewById(R.id.deg))).setText(item.temp + "°");
        ((TextView)(adapterLayout.findViewById(R.id.hi))).setText(item.hi + "°");
        ((TextView)(adapterLayout.findViewById(R.id.lo))).setText(item.lo + "°");
        ((TextView)(adapterLayout.findViewById(R.id.description))).setText(item.desc);
        ((TextView)(adapterLayout.findViewById(R.id.timeAndLocation))).setText(item.time + "\t" + item.location);

        //Thunderstorm, Drizzle, Rain, Snow, a bunch of atmospheric conditions, Clear, Clouds
        ImageView cat = adapterLayout.findViewById(R.id.imageView);
        TextView quote = adapterLayout.findViewById(R.id.quote);
        switch(item.desc) {
            case "Clear":
                cat.setImageResource(R.drawable.sunshinekitty);
                quote.setText("Purrrrr");
                break;
            case "Drizzle":
            case "Rain":
                cat.setImageResource(R.drawable.rainkitty);
                quote.setText("Mraww");
                break;
            case "Thunderstorm":
                cat.setImageResource(R.drawable.thunderkitty);
                quote.setText("HISSSSSS");
                break;
            case "Snow":
                cat.setImageResource(R.drawable.snowkitty);
                quote.setText("Meeeeeeeeeeeew");
                break;
            case "Clouds":
                cat.setImageResource(R.drawable.cloudykitty);
                quote.setText("Zzzzzz");
                break;
            default:
                cat.setImageResource(R.drawable.foggykitty);
                quote.setText("MEOW MEOW Meow meow mew m");
                break;
        }

        return adapterLayout;
    }
}
