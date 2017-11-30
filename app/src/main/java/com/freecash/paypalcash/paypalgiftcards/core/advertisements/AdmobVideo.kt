package com.freecash.paypalcash.paypalgiftcards.core.advertisements

import android.content.Context
import android.widget.TextView
import com.freecash.paypalcash.paypalgiftcards.core.analytics.Analytics
import com.freecash.paypalcash.paypalgiftcards.core.managers.CoinsManager
import com.freecash.paypalcash.paypalgiftcards.core.managers.PreferencesManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener

class AdmobVideo(private var coinsManager: CoinsManager,
        private var preferencesManager: PreferencesManager,
        context: Context) {

    private var rewardedVideo: RewardedVideoAd? = null

    private lateinit var coinsText: TextView

    init {

            rewardedVideo = MobileAds.getRewardedVideoAdInstance(context)
            rewardedVideo?.rewardedVideoAdListener = object : RewardedVideoAdListener {
                override fun onRewardedVideoAdClosed() {}
                override fun onRewardedVideoAdLeftApplication() {}
                override fun onRewardedVideoAdLoaded() {}
                override fun onRewardedVideoAdOpened() {}
                override fun onRewarded(rewardItem: RewardItem?) {
                    if (!preferencesManager.get(PreferencesManager.ADDITIONAL_LIFE, false)) {
                        coinsManager.addCoins(rewardItem?.amount!!)
                        coinsText.text = coinsManager.getCoins().toString()
                        Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.REWARD)
                    } else {
                        preferencesManager.put(PreferencesManager.ADDITIONAL_LIFE, false)
                        preferencesManager.put(PreferencesManager.TICKETS_LIFES, 1)
                        Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.GAME_BOOST)
                    }
                }
                override fun onRewardedVideoStarted() {}
                override fun onRewardedVideoAdFailedToLoad(p0: Int) {}
            }
            loadVideo()

    }

    private fun loadVideo() {
        rewardedVideo?.loadAd("ca-app-pub-7065666432812754/7301736454",
                AdRequest.Builder().build())
    }

    fun show(coinsView: TextView) : Boolean {
        coinsText = coinsView
        if (rewardedVideo != null) {
            if (rewardedVideo?.isLoaded!!) {
                rewardedVideo?.show()
                Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.OPEN)
                return true
            }
        }
        return false
    }
}
