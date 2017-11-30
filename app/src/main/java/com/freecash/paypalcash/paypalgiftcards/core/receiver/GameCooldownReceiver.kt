package com.freecash.paypalcash.paypalgiftcards.core.receiver

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.NotificationCompat
import com.freecash.paypalcash.paypalgiftcards.R
import com.freecash.paypalcash.paypalgiftcards.screens.GameActivity

class GameCooldownReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var nm = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var ni = Intent(context, GameActivity::class.java)
        var content = PendingIntent.getActivity(context, 0, ni, 0)

        nm.notify(0, NotificationCompat.Builder(context).setContentTitle(context.getString(R.string.app_name))
                .setContentText("Time to play! Tickets cooldown finished!").setSmallIcon(R.drawable.coin)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo))
                .setContentIntent(content).setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND).build())
    }
}
