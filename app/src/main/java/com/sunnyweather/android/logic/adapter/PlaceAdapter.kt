package com.sunnyweather.android.logic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.databinding.ItemPlaceBinding
import com.sunnyweather.android.logic.model.Place

class PlaceAdapter(var placeList: List<Place>) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    var onClick: ((Place) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = placeList[position]
        holder.binding.placeBean = bean

        holder.itemView.setOnClickListener {
            onClick?.invoke(bean)

        }
    }

    class ViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)
}


