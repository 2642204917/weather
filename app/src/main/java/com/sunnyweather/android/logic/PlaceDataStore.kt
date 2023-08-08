package com.sunnyweather.android.logic

import android.app.Application

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.mApplication.Companion.application
import kotlinx.coroutines.flow.first

object PlaceDataStore {

    private const val PLACEID = "placeId"
    private const val PLACENAME = "placeName"
    private const val PLACEADDRESS = "placeAddress"
    private const val PLACELAT = "placeLat"
    private const val PLACELNG = "placeLng"
    private val Application.dataStore: DataStore<Preferences> by preferencesDataStore(name = "place")
    private val placeIdKey by lazy { stringPreferencesKey(PLACEID) }
    private val placeNameKey by lazy { stringPreferencesKey(PLACENAME) }
    private val placeAddressKey by lazy { stringPreferencesKey(PLACEADDRESS) }
    private val placeLatKey by lazy { doublePreferencesKey(PLACELAT) }
    private val placeLngKey by lazy { doublePreferencesKey(PLACELNG) }
    suspend fun getPlace(): Place? {
        application.dataStore.data.first().also {
            val id = it[placeIdKey] ?: return null
            val name = it[placeNameKey] ?: return null
            val address = it[placeAddressKey] ?: return null
            val lat = it[placeLatKey] ?: return null
            val lng = it[placeLngKey] ?: return null
            return Place(id, name, address, Location(lat, lng))
        }
    }

    suspend fun savePlace(place: Place) {

        application.dataStore.edit {
            it[placeIdKey] = place.id
            it[placeNameKey] = place.name
            it[placeAddressKey] = place.formattedAddress
            it[placeLatKey] = place.location.lat
            it[placeLngKey] = place.location.lng

        }

    }
}