package com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.presenter

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.core.os.bundleOf
import com.example.mobiledevelopmentcourselabapp.core.presentation.BasePresenter
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.model.NotificationModel
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.receiver.NotificationsReceiver
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view.NotificationAddView
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class NotificationAddPresenter @Inject constructor(
    private val context: Context
) : BasePresenter<NotificationAddView>() {

    private var text: String = ""
    private var date = LocalDate.now()
    private var time = LocalTime.now()

    fun onDateClick() {
        viewState.showDatePicker()
    }

    fun onTimeClick() {
        viewState.showTimePicker()
    }

    fun onDoneClick() {
        val result = NotificationModel(Date().time.toInt(), text, LocalDateTime.of(date, time))
        viewState.backWithResult(bundleOf(NOTIFICATION_RESULT to result))
        saveNotification(result)
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun saveNotification(notificationModel: NotificationModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val time = notificationModel.dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotificationsReceiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putLong("NOTIFICATION_TIME", time)
                putInt("NOTIFICATION_ID", notificationModel.id)
                putString("NOTIFICATION", notificationModel.note)
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            notificationModel.id,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            notifyPendingIntent
        )
    }


    fun onTextChanged(text: Editable?) {
        this.text = text?.toString().orEmpty()
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        date = LocalDate.of(year, month + 1, day)
        viewState.setDate(date.toString())
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        time = LocalTime.of(hour, minute)
        viewState.showTime(time.toString())
    }

    companion object {
        const val NOTIFICATION_RESULT = "NOTIFICATION_RESULT"
    }
}