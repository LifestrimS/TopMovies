package com.lifestrim.topmovies.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val service = Intent(context, NotificationService::class.java)
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
        service.putExtra("movieTitle", intent.getStringExtra("movieTitle"))
        context.startService(service)
    }

}