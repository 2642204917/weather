package com.sunnyweather.android.logic.model

data class PlaceResponseBean(val status: String, val query: String, val places: List<Place>)


data class Place(
    val id: String,
    val name: String,
    val formattedAddress: String,
    val location: Location
)

data class Location(val lat: Double, val lng: Double)
