package com.freecash.paypalcash.paypalgiftcards.inject

import com.freecash.paypalcash.paypalgiftcards.core.MyApplication
import com.freecash.paypalcash.paypalgiftcards.core.services.ClaimService
import com.freecash.paypalcash.paypalgiftcards.dialogs.LoginDialog
import com.freecash.paypalcash.paypalgiftcards.dialogs.PromocodeDialog
import com.freecash.paypalcash.paypalgiftcards.dialogs.SignupDialog
import com.freecash.paypalcash.paypalgiftcards.screens.BaseActivity
import dagger.Component

@Component(modules = arrayOf(AppModule::class, MainModule::class))
interface AppComponent {

    fun inject(screen: BaseActivity)
    fun inject(app: MyApplication)
    fun inject(dialog: LoginDialog)
    fun inject(dialog: SignupDialog)
    fun inject(dialog: PromocodeDialog)
    fun inject(service: ClaimService)
}
