package com.freecash.paypalcash.paypalgiftcards.core.managers

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    companion object {
        var FIRST_LAUNCH = "FIRST_LAUNCH"
        var INVITE_CODE = "INVITE_CODE"

        var CLAIM_MINUTES = "CLAIM_MINUTES"
        var CLAIM_SECONDS = "CLAIM_SECONDS"

        var TICKETS_LIFES = "TICKETS_LIFES"
        var TICKETS_TIME = "TICKETS_TIME"

        var USERNAME = "USERNAME"
        var PASSWORD = "PASSWORD"

        var NATIVEX_KEY = "NATIVEX_KEY"

        var ADDITIONAL_ATTEMPT = "ADDITIONAL_ATTEMPT"
        var ADDITIONAL_LIFE = "ADDITIONAL_LIFE"

        var LAST_SAVED = "LAST_SAVED"
        var LAST_CHECKED = "LAST_CHECKED"
    }

    init {
        sharedPreferences = context.getSharedPreferences(null, Context.MODE_MULTI_PROCESS)
    }

    fun <T> put(key: String, value: T) {
        when (value) {
            is String -> sharedPreferences!!.edit().putString(key, value).apply()
            is Int -> sharedPreferences!!.edit().putInt(key, value).apply()
            is Long -> sharedPreferences!!.edit().putLong(key, value).apply()
            is Float -> sharedPreferences!!.edit().putFloat(key, value).apply()
            is Boolean -> sharedPreferences!!.edit().putBoolean(key, value).apply()
            else -> {}
        }
    }

    fun <T> get(key: String, default: T) : T {
        when (default) {
            is String -> return sharedPreferences?.getString(key, default) as T
            is Int -> return sharedPreferences?.getInt(key, default) as T
            is Long -> return sharedPreferences?.getLong(key, default) as T
            is Float -> return sharedPreferences?.getFloat(key, default) as T
            is Boolean -> return sharedPreferences?.getBoolean(key, default) as T
            else -> { return null as T }
        }
        return null as T
    }

    fun deleteAll() {
        sharedPreferences!!.edit().clear().apply()
    }
}
