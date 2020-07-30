package com.lifestrim.topmovies.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import java.util.*
import android.app.NotificationChannel
import com.lifestrim.topmovies.R

class NotificationService : IntentService("NotificationService") {
    private lateinit var mNotification: Notification
    private val mNotificationId: Int = 1000

    companion object {
        const val CHANNEL_ID = "com.lifestrim.topmovies.notification.CHANNEL_ID"
        const val CHANNEL_NAME = "About viewing"
    }

    @SuppressLint("NewApi")
    private fun createChannel() {

        val context = this.applicationContext
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        notificationChannel.enableVibration(true)
        notificationChannel.setShowBadge(true)
        notificationChannel.enableLights(true)
        notificationChannel.description = getString(R.string.notification_channel_description)
        notificationManager.createNotificationChannel(notificationChannel)
    }


    override fun onHandleIntent(intent: Intent?) {

        createChannel()
        var timestamp: Long = 0
        var message = "You wanted to watch this movie: "
        if (intent != null && intent.extras != null) {
            timestamp = intent.extras!!.getLong("timestamp")
            message += intent.extras!!.getString("movieTitle").toString()
        }

        if (timestamp > 0) {

            val title = "How about some movies?"

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp

            val res = this.resources

            mNotification = Notification.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setStyle(
                    Notification.BigTextStyle()
                        .bigText(message)
                )
                .setContentText(message).build()

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(mNotificationId, mNotification)
        }


    }
}