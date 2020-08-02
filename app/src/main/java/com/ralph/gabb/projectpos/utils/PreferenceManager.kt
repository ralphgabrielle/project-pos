package com.ralph.gabb.projectpos.utils

import android.content.Context
import android.content.SharedPreferences


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
class PreferenceManager constructor(context: Context){

    companion object {
        const val SHARED_PREFERENCE_NAME = "com.ralph.gabb.projectpos.SHARED_PREFERENCE_NAME"
    }

    private var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun clearAll() {
        pref.edit().apply {
            clear()
            apply()
        }
    }

    fun set(pair: Pair<String, Any?>) {
        pref.edit().apply{
            when (val value = pair.second) {
                is String -> putString(pair.first, value)
                is Int -> putInt(pair.first, value)
                is Boolean -> putBoolean(pair.first, value)
            }

//            HashMap<String, String>().get()

            apply()
        }
    }

    fun <T> get(key: String, defaultValue: T) : Any? {
        return when (defaultValue) {
            is String -> return pref.getString(key, defaultValue)
            is Int -> return pref.getInt(key, defaultValue)
            is Boolean -> return pref.getBoolean(key, defaultValue)
            else -> null
        }
    }
}