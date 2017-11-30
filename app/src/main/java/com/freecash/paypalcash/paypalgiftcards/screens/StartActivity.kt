package com.freecash.paypalcash.paypalgiftcards.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick

import com.freecash.paypalcash.paypalgiftcards.R
import com.freecash.paypalcash.paypalgiftcards.core.managers.PreferencesManager

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (!preferencesManager.get(PreferencesManager.USERNAME, "").isEmpty() and
                !preferencesManager.get(PreferencesManager.PASSWORD, "").isEmpty()) {
            goOffers()
        } else {
            bind()
        }
    }

    @OnClick(R.id.login, R.id.register)
    fun control(v: View) {
        when (v.id) {
            R.id.login -> {
                dialogsManager.showLoginDialog(supportFragmentManager, {
                    dialogsManager.showAlertDialog(supportFragmentManager, "You've been sucessfully logged in!", {
                        goOffers()
                    })
                })
            }
            R.id.register -> {
                dialogsManager.showSignupDialog(supportFragmentManager, {
                    dialogsManager.showAlertDialog(supportFragmentManager, "You've been successfully signed up!", {
                        dialogsManager.showPromoDialog(supportFragmentManager, {
                            goOffers()
                        })
                    })
                })
            }
        }
    }

    private fun goOffers() {
        startActivity(Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}
