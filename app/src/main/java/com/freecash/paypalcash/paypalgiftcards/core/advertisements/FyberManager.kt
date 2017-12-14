package com.freecash.paypalcash.paypalgiftcards.core.advertisements

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.freecash.paypalcash.paypalgiftcards.core.analytics.Analytics
import com.fyber.Fyber
import com.fyber.ads.AdFormat
import com.fyber.currency.VirtualCurrencyErrorResponse
import com.fyber.currency.VirtualCurrencyResponse
import com.fyber.requesters.*
import com.freecash.paypalcash.paypalgiftcards.core.managers.CoinsManager
import com.freecash.paypalcash.paypalgiftcards.core.managers.PreferencesManager
import com.freecash.paypalcash.paypalgiftcards.screens.OffersActivity
import kotlinx.android.synthetic.main.toolbar_back.*

class FyberManager(
        private var coinsManager: CoinsManager,
        private var preferencesManager: PreferencesManager) {

    private lateinit var coinView: TextView
    private var isAvailable = false
    private var offerWallIntent: Intent? = null

    private var activity: AppCompatActivity? = null

    fun init(activity: AppCompatActivity) {
        this.activity = activity
        Fyber.with(appId, activity)
                .withSecurityToken(SecurityToken)
                .start()
        OfferWallRequester.create(object : RequestCallback {

            override fun onAdNotAvailable(p0: AdFormat?) {
                isAvailable = false
            }

            override fun onRequestError(p0: RequestError?) {
                isAvailable = false
            }

            override fun onAdAvailable(offerWallIntent: Intent?) {
                this@FyberManager.offerWallIntent = offerWallIntent
                isAvailable = true
            }
        }).request(activity)

        VirtualCurrencyRequester.create(callback).request(activity)
    }

    private var callback = object : VirtualCurrencyCallback {
        override fun onSuccess(p0: VirtualCurrencyResponse?) {
            if (p0?.deltaOfCoins?.toInt() ?: 0 > 0) {
                coinsManager.addCoins(p0?.deltaOfCoins?.toInt() ?: 0)
                try {
                    coinView.text = coinsManager.getCoins().toString()
                } catch (ex: Exception) {
                    try {
                        (activity as OffersActivity).coinsView.text = coinsManager.getCoins().toString()
                    } catch (ex: Exception) {}
                }
                Analytics.report(Analytics.OFFER, Analytics.FYBER, Analytics.REWARD)
            }
        }

        override fun onRequestError(p0: RequestError?) {}
        override fun onError(p0: VirtualCurrencyErrorResponse?) {}
    }

    fun onResume(activity: AppCompatActivity) {
        this.activity = activity
        VirtualCurrencyRequester.create(callback).request(activity)
    }

    fun show(activity: AppCompatActivity, coinsView: TextView): Boolean {
        this.activity = activity
        this.coinView = coinsView
        if (isAvailable) activity.startActivity(offerWallIntent)
        Analytics.report(Analytics.OFFER, Analytics.FYBER, Analytics.OPEN)
        return isAvailable

    }

    companion object {
        val appId = "108338"
        val SecurityToken = "4f8da7aec1cdb2a4a4f38bbdc733e5af"
    }
}
