package com.freecash.paypalcash.paypalgiftcards.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.freecash.paypalcash.paypalgiftcards.AppTools
import com.freecash.paypalcash.paypalgiftcards.R
import com.freecash.paypalcash.paypalgiftcards.data.Card
import com.vicpin.krealmextensions.delete
import kotlinx.android.synthetic.main.redeem_activity.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.concurrent.thread

class RedeemActivity : BaseActivity(){

    private var cardPrice: Int = 0
    private var cardType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.redeem_activity)

        bindCoinView()
        bind()

        toolbarText.text = "Redeem"

        initBanner()

        initCard()
    }

    @OnClick(R.id.addCoinsText)
    fun back(view: View) {
        startActivity(Intent(this, GiftCardsActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    private fun initCard() {
        cardPrice = intent.getIntExtra("price", 0)
        cardType = intent.getIntExtra("type", 0)
        if (cardType == 1) {
            redeem_card.setImageDrawable(resources.getDrawable(when (cardPrice) {
                6000 -> {
                    R.drawable.card_25
                }
                10000 -> {
                    R.drawable.card_50
                }
                14000 -> {
                    R.drawable.card_75
                }
                18000 -> {
                    R.drawable.card_100
                }
                24000 -> {
                    R.drawable.card_150
                }
                30000 -> {
                    R.drawable.card_200
                }
                else -> {
                    0
                }
            }))
        }
    }

    @OnClick(R.id.redeemBtn)
    fun redeem() {
        if (AppTools.isNetworkAvaliable(this)) {
            if (AppTools.isEmailAdressCorrect(emailText.text.toString())) {
                var dismisser = dialogsManager.showProgressDialog(supportFragmentManager)
                thread {
                    Thread.sleep(3000)
                    runOnUiThread {
                        dismisser.dismiss()
                        Card().delete { q -> q.equalTo("price", cardPrice); q.equalTo("type", cardType) }
                        dialogsManager.showAlertDialog(supportFragmentManager,
                                "You will receive your Gift Card in 3 - 7 days!", {
                            onBackPressed()
                        })
                    }
                }
            } else {
                dialogsManager.showAlertDialog(supportFragmentManager,
                        "Email is not valid!", {
                    admobInterstitial?.show {  }
                })
            }
        } else {
            dialogsManager.showAlertDialog(supportFragmentManager,
                    "No internet connection!", {
                admobInterstitial?.show {  }
            })
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, GiftCardsActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}
