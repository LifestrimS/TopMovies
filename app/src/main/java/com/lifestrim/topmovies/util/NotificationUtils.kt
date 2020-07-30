package com.lifestrim.topmovies.util

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.view.View
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.lifestrim.topmovies.R
import com.lifestrim.topmovies.notification.AlarmReceiver
import kotlinx.android.synthetic.main.data_time_dialog.view.*
import java.util.*

class NotificationUtils {


    fun setDateTimeForNotification(view: View, movieTitle: String) {
        val mDialogView = LayoutInflater.from(view.context).inflate(R.layout.data_time_dialog, null)
        val mBuilder = AlertDialog.Builder(view.context)
            .setView(mDialogView)
            .setTitle(view.context.getString(R.string.time_date_dialog_title))

        mDialogView.timePicker.setIs24HourView(true)
        val mAlertDialog = mBuilder.show()

        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }

        mDialogView.dialogAddBtn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(
                mDialogView.datePicker.year,
                mDialogView.datePicker.month,
                mDialogView.datePicker.dayOfMonth,
                mDialogView.timePicker.hour,
                mDialogView.timePicker.minute,
                0
            )
            val dateLong = calendar.timeInMillis

            setNotification(view, dateLong, movieTitle)
            mAlertDialog.dismiss()
        }
    }

    private fun setNotification(view: View, timeInMilliSeconds: Long, movieTitle: String) {

        if (timeInMilliSeconds > 0) {

            val alarmManager = view.context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(view.context.applicationContext, AlarmReceiver::class.java)

            alarmIntent.putExtra("timestamp", timeInMilliSeconds)
            alarmIntent.putExtra("movieTitle", movieTitle)


            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds

            val pendingIntent = PendingIntent.getBroadcast(
                view.context,
                0,
                alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        }

    }


}
