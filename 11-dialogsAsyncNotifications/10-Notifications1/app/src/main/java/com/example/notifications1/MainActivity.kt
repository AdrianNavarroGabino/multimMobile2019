package com.example.notifications1

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "com.example.notifications1"
    private val notificationId = 123456

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nbuilder = NotificationCompat.Builder(this, CHANNEL_ID)

        val intent = Intent(this, RequestNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        nbuilder.apply {
            setSmallIcon(R.mipmap.ic_launcher_round)
            setContentTitle("Notification")
            setContentText("Test notification!!")
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }

        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                with(NotificationManagerCompat.from(this@MainActivity)) {
                    notify(notificationId, nbuilder.build())
                }

                Toast.makeText(applicationContext, "Finish", Toast.LENGTH_SHORT).show()
            }
        }

        notifBtn.setOnClickListener {
            timer.start()
        }
    }
}
