package com.sunnyweather.android.logic

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sunnyweather.android.logic.model.Place

import com.sunnyweather.android.mApplication
import com.sunnyweather.android.mApplication.Companion.application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object PlaceDataStore {

    private const val PLACE = "place"
    val Application.dataStore: DataStore<Preferences> by preferencesDataStore(name = "place")
    val placeKey by lazy { stringPreferencesKey(PLACE) }
    fun getPlace(): Flow<String> {

        return application.dataStore.data
            .map { preferences ->
                preferences[placeKey] ?: ""
            }
    }

    suspend fun savePlace(place: Place) {

        application.dataStore.edit {
            it[placeKey] = Gson().toJson(place)
        }

    }
}