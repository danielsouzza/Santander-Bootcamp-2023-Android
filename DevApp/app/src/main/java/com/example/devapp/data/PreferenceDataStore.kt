package com.example.devapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "string")

class PreferenceDataStore(context: Context) {
    private val pref = context.dataStore
    companion object{
        val username = stringPreferencesKey("USERNAME")
    }

    suspend fun saveUsername(value: String){
        pref.edit {it[username] = value }
    }

    suspend fun getUsername(): String? = pref.data.first()[username]
}