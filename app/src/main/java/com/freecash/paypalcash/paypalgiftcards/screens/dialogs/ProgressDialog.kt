package com.freecash.paypalcash.paypalgiftcards.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.freecash.paypalcash.paypalgiftcards.R

class ProgressDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        return inflater?.inflate(R.layout.progress_layout, container, false)
    }
}
