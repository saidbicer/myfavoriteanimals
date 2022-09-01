package com.said.myfavoriteanimals.util

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesUtils @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val stringKey = "lastimg"

    fun getLastTakenImgUrl(): String? {
        return sharedPreferences.getString(stringKey, "")
    }

    fun setLastTakenImgUrl(url: String?) {
        sharedPreferences.edit().putString(stringKey, url).apply()
    }

    fun clearLastImgUrl() {
        setLastTakenImgUrl("")
    }
}