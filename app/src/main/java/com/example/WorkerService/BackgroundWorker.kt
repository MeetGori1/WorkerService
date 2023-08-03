package com.example.WorkerService

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters



class BackgroundWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    var uniqueId = 123
    private var CHANNEL_ID1 = "id"
    private var CHANNEL_NAME1 = "name"


    override fun doWork(): Result {
        createDefaultChannel()
        sendNotification()
        Toast.makeText(applicationContext, "water", Toast.LENGTH_SHORT).show()
        return Result.success()
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(

    ) {
        val builder1 = NotificationCompat.Builder(applicationContext, CHANNEL_ID1).setStyle(
            NotificationCompat.BigTextStyle().setBigContentTitle("water")
                .bigText("water")
        ).setContentTitle("water")
            .setOnlyAlertOnce(true)
            .setContentText("water")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
        NotificationManagerCompat.from(context).notify(uniqueId, builder1.build())
    }

    private fun createDefaultChannel() {
        val context: Context = applicationContext

        val sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val attributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        val channel = NotificationChannel(
            CHANNEL_ID1,
            CHANNEL_NAME1,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.setSound(sound, attributes)
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }


}