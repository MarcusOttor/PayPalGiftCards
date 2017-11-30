package com.freecash.paypalcash.paypalgiftcards

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import java.util.*
import java.util.regex.Pattern

object AppTools {

    fun isNetworkAvaliable(context: Context): Boolean {
        var connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var info = connectivity.activeNetworkInfo

        return info != null && info.isConnectedOrConnecting
    }

    fun isEmailAdressCorrect(email: String) : Boolean {
        var pattern = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,})$")
        var matcher = pattern.matcher(email)
        return (matcher.matches() and (email.length > 5))
    }

    fun uniqueId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun checkCode(code: String) : Boolean {
        try {
            var sum = code[2].toString().toInt() + code[3].toString().toInt() + code[5].toString().toInt() + code[7].toString().toInt() + code[8].toString().toInt()
            return ((sum == 30) and (code.length == 9))
        } catch (ex: Exception) {
            return false
        }
    }

    fun createCode() : String {
        var ref = ""
        var digits: String
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val numbers = "0123456789"
        while (true) {
            digits = ""
            for (i in 0..4) {
                digits += numbers[Random().nextInt(9)]
            }
            var sum = 0
            for (i in 0..4) {
                sum += Integer.valueOf(digits[i].toString())!!
            }
            if (sum == 30) {
                break
            }
        }
        ref += alphabet[Random().nextInt(25)].toString() + alphabet[Random().nextInt(25)].toString() +
                digits[0] + digits[1] + alphabet[Random().nextInt(25)].toString() + digits[2] +
                alphabet[Random().nextInt(25)].toString() + digits[3] + digits[4]

        return ref
    }
}
