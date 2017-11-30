package com.freecash.paypalcash.paypalgiftcards.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import butterknife.ButterKnife
import butterknife.OnClick
import com.freecash.paypalcash.paypalgiftcards.AppTools
import com.freecash.paypalcash.paypalgiftcards.R
import com.freecash.paypalcash.paypalgiftcards.core.MyApplication
import com.freecash.paypalcash.paypalgiftcards.core.managers.CoinsManager
import com.freecash.paypalcash.paypalgiftcards.core.managers.DialogsManager
import com.freecash.paypalcash.paypalgiftcards.core.managers.PreferencesManager
import com.freecash.paypalcash.paypalgiftcards.core.managers.RetrofitManager
import com.freecash.paypalcash.paypalgiftcards.data.Card
import com.freecash.paypalcash.paypalgiftcards.inject.AppModule
import com.freecash.paypalcash.paypalgiftcards.inject.DaggerAppComponent
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.dialog_login.view.*
import javax.inject.Inject

class LoginDialog(private var onLogin: () -> Unit) : DialogFragment() {

    @Inject lateinit var retrofitManager: RetrofitManager
    @Inject lateinit var preferencesManager: PreferencesManager
    @Inject lateinit var coinsManager: CoinsManager
    @Inject lateinit var dialogsManager: DialogsManager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .mainModule((activity.application as MyApplication).mainModule)
                .build().inject(this)

        var view = inflater?.inflate(R.layout.dialog_login, container, false)

        ButterKnife.bind(this, view!!)

        return view
    }

    @OnClick(R.id.login)
    fun login() {
        if (AppTools.isNetworkAvaliable(context)) {
            if ((root().usernameTxt.text.length >= 4) and (root().passwordTxt.text.length >= 4)) {
                var progress = dialogsManager.showProgressDialog(activity.supportFragmentManager)
                retrofitManager.login(root().usernameTxt.text.toString(),
                        root().passwordTxt.text.toString(), {
                    preferencesManager.put(PreferencesManager.USERNAME, root().usernameTxt.text.toString())
                    preferencesManager.put(PreferencesManager.PASSWORD, root().passwordTxt.text.toString())
                    retrofitManager.getdata(preferencesManager.get(PreferencesManager.USERNAME, ""),
                            preferencesManager.get(PreferencesManager.PASSWORD, ""), { data ->
                        retrieveData(data)
                        getInviteCode(progress)
                    }, {
                        getInviteCode(progress)
                    })
                }, {
                    progress.dismiss()
                    dialogsManager.showAlertDialog(activity.supportFragmentManager,
                            "Unable to login, something went wrong!", {})
                })
            } else {
                dialogsManager.showAlertDialog(activity.supportFragmentManager,
                        "Login data is too short!", {})
            }
        } else {
            dialogsManager.showAlertDialog(activity.supportFragmentManager,
                    "Sorry, no internet connection!", {})
        }
    }

    private fun retrieveData(data: String) {
        if (!data.isEmpty()) {
            var dataType = data.split("|")
            try {
                coinsManager.setCoins(dataType[0].split("=")[1].toInt())
                var boosters = dataType[1].split("@")
                if (!boosters.isEmpty()) {
                    boosters.forEach { b ->
                        try {
                            var cardPart = b.split("#")
                            if (cardPart.isNotEmpty()) {
                                Card(cardPart[0].toInt(), cardPart[1].toInt()).save()
                            }
                        } catch (ex: Exception) {
                        }
                    }
                }
            } catch (ex: Exception) {
            }
        }
    }

    private fun getInviteCode(progress: ProgressDialog) {
        retrofitManager.getinvite(root().usernameTxt.text.toString(), { invite ->
            if (!invite.isEmpty()) {
                preferencesManager.put(PreferencesManager.INVITE_CODE, invite)
            }
            progress.dismiss()
            onLogin()
        }, {
            progress.dismiss()
            onLogin()
        })
    }

    @OnClick(R.id.loginCancel)
    fun cancel() {
        dismiss()
    }

    fun root(): View {
        return view?.rootView!!
    }
}
