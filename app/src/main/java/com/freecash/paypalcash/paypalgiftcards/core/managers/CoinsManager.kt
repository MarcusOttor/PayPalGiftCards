package com.freecash.paypalcash.paypalgiftcards.core.managers

import android.content.Context
import android.content.SharedPreferences

class CoinsManager(context: Context) {

    companion object {
        var COINS_CURRENT = "COINS_CURRENT"
    }

    private var prefs: SharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE)

    fun deleteall() {
        prefs.edit().clear().apply()
    }

    fun subtractCoins(coins: Int) {
        prefs.edit().putInt(COINS_CURRENT, getCoins() - coins).apply()
    }

    fun getCoins() : Int {
        return prefs.getInt(COINS_CURRENT, 0)
    }

    fun addCoins(coins: Int) {
        prefs.edit().putInt(COINS_CURRENT, getCoins() + coins).apply()
    }

    fun setCoins(coins: Int) {
        prefs.edit().putInt(COINS_CURRENT, coins).apply()
    }
}
