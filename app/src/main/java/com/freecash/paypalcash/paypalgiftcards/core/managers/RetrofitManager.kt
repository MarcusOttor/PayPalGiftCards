package com.freecash.paypalcash.paypalgiftcards.core.managers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {

    fun build() : Request {
        return Retrofit.Builder().baseUrl("http://freecashgiftcards.hol.es")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Request::class.java)
    }

    fun login(user: String, pass: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        build().login(user, pass).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response?.body().toString().toBoolean()) {
                    onSuccess()
                } else {
                    onFail()
                }
            }
        })
    }

    fun signup(user: String, pass: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        build().signup(user, pass).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response?.body().toString().toBoolean()) {
                    onSuccess()
                } else {
                    onFail()
                }
            }
        })
    }

    fun checkinvite(code: String, exist: () -> Unit, notexist: () -> Unit) {
        build().checkinvite(code).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response?.body().toString().toBoolean()) {
                    exist()
                } else {
                    notexist()
                }
            }
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                notexist()
            }
        })
    }

    fun getinvite(user: String, onSuccess: (String) -> Unit, onFail: () -> Unit) {
        build().getinvite(user).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                onSuccess(response?.body().toString())
            }
        })
    }

    fun invitecoins(user: String, pass: String, onSuccess: (Int) -> Unit, onFail: () -> Unit) {
        build().invitesatoshi(user, pass).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                try {
                    onSuccess(response?.body().toString().toInt())
                } catch (ex: Exception) {
                    onSuccess(0)
                }
            }
        })
    }

    fun getdata(user: String, pass: String, onSuccess: (String) -> Unit, onFail: () -> Unit) {
        build().getdata(user, pass).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                onSuccess(response?.body().toString())
            }
        })
    }

    fun savedata(user: String, pass: String, data: String, onSuccess: () -> Unit, onFail: () -> Unit) {
        build().savedata(user, pass, data).enqueue(object : Callback<Any> {
            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                onFail()
            }
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                if (response?.body().toString().toBoolean()) {
                    onSuccess()
                } else {
                    onFail()
                }
            }
        })
    }
}
