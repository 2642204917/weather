package com.sunnyweather.android.logic.model

data class PlaceResponseBean(val status: String, val query: String, val places: List<Place>) :
    BaseResponseBean()

data class Place(
    val id: String,
    val name: String,
    val formatted_address: String,
    val location: Location
)

data class Location(val lat: Double, val lng: Double)
