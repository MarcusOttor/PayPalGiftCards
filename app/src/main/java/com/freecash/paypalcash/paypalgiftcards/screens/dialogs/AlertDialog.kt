package com.freecash.paypalcash.paypalgiftcards.screens.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import butterknife.ButterKnife
import butterknife.OnClick
import com.freecash.paypalcash.paypalgiftcards.R
import kotlinx.android.synthetic.main.info_dialog.view.*

class AlertDialog(private var alert: String, private var onOk: () -> Unit) : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        var view = inflater?.inflate(R.layout.info_dialog, container, false)

        ButterKnife.bind(this, view!!)

        view.alert.text = alert

        return view
    }

    @OnClick(R.id.ok)
    fun ok() {
        dismiss()
        onOk()
    }
}
