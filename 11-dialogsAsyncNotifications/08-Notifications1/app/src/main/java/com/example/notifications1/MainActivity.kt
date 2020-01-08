package com.example.notifications1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "com.example.notifications1"
    private val notificationId = 123456

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nbuilder = NotificationCompat.Builder(this, CHANNEL_ID)

        nbuilder.apply {
            setSmallIcon(R.mipmap.ic_launcher_round)
            setContentTitle("Notification")
            setContentText("Test notification!!")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        notifBtn.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, nbuilder.build())
            }
        }
    }
}
