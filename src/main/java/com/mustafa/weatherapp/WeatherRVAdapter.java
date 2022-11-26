package com.mustafa.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafa.weatherapp.databinding.WeatherRvItemBinding;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherRVModel> weatherRVModelArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WeatherRvItemBinding binding = WeatherRvItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {

        WeatherRVModel model = weatherRVModelArrayList.get(position);

        holder.binding.TVTemperature.setText(weatherRVModelArrayList.get(position).temperature+"°C");
        Picasso.get().load("http:".concat(weatherRVModelArrayList.get(position).icon)).into(holder.binding.IVCondition);
        holder.binding.TVWindSpeed.setText(weatherRVModelArrayList.get(position).windSpeed+"Km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = input.parse(weatherRVModelArrayList.get(position).time);
            assert t != null;
            holder.binding.TvTime.setText(output.format(t));
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        WeatherRvItemBinding binding;

        public ViewHolder(WeatherRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
