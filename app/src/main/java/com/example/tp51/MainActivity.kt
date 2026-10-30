package com.example.notificationapp

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private var notifyButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifyButton = findViewById<Button?>(R.id.notifyButton)

        // Créer le canal de notification
        createNotificationChannel()

        // Action du bouton
        notifyButton!!.setOnClickListener(View.OnClickListener { v: View? ->
            showNotification()
        })
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Mon Canal"
            val description = "Canal pour les notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    name,
                    importance
                )

            channel.setDescription(description)

            val manager =
                getSystemService<NotificationManager?>(NotificationManager::class.java)

            manager.createNotificationChannel(channel)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification() {
        // Intent pour ouvrir MainActivity

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )

        // Construire la notification
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title")
                .setContentText("This is a notification message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        // Afficher la notification
        val notificationManager =
            NotificationManagerCompat.from(this)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun setContentTitle(string: String) {
        TODO("Not yet implemented")
    }

    private fun setContentTitle(string: String) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val CHANNEL_ID = "my_notification_channel"
        private const val NOTIFICATION_ID = 1
    }
}