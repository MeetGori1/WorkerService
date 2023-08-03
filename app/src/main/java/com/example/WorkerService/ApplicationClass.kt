package com.example.WorkerService

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationManagerCompat

class ApplicationClass:Application() {
    override fun onCreate() {
        super.onCreate()
        createDefaultChannel()
    }

    private fun createDefaultChannel() {
        val context: Context = applicationContext

        val sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val attributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        val channel = NotificationChannel(
            "running_channel",
            "running_channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.setSound(sound, attributes)
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }
}