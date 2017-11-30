package com.freecash.paypalcash.paypalgiftcards.core.analytics

import com.yandex.metrica.YandexMetrica

object Analytics {

    var INTERSTITIAL = "INTERSTITIAL"
    var OFFER = "OFFER"
    var VIDEO = "VIDEO"
    var WITHDRAW = "WITHDRAW"

    var OPEN = "OPEN"
    val REWARD = "REWARD"
    var AMOUNT = "AMOUNT"

    var BANNER = "BANNER"
    var CLICKED = "CLICKED"

    var MOOFFERS = "MOOFFERS"
    var ADMOB = "ADMOB"
    var ADCOLONY = "ADCOLONY"
    var APPNEXT = "APPNEXT"
    var UNITY = "UNITY"
    var OFFERTORO = "OFFERTORO"
    var VUNGLE = "VUNGLE"
    var NATIVEX = "NATIVEX"
    var FYBER = "FYBER"

    var GAME_BOOST = "GAME_BOOST"

    var NOTIFICATION = "NOTIFICATION"

    fun report(event: String, name: String, key: String) {
        var action = HashMap<String, Any>()
        action.put(name, key)
        YandexMetrica.reportEvent(event, action)
    }

    fun report(event: String, key: String) {
        YandexMetrica.reportEvent(event, key)
    }

    fun report(event: String) {
        YandexMetrica.reportEvent(event)
    }
}
