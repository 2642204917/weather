package com.sunnyweather.android.logic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.databinding.ItemForecastBinding
import com.sunnyweather.android.logic.model.Skycon
import com.sunnyweather.android.logic.model.Temperature

class ForecastAdapter(var weatherList: List<Skycon>, var temperatureList: List<Temperature>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemForecastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]
        val temperature = temperatureList[position]
        holder.binding.skyCon = weather
        holder.binding.temperature = temperature
    }

    class ViewHolder(val binding: ItemForecastBinding) : RecyclerView.ViewHolder(binding.root)
}


