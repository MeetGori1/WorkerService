package com.example.WorkerService

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class RunningService : Service() {
    private lateinit var timer: CountDownTimer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> {
                timer.cancel()
                start()
            }

            Actions.STOP.toString() -> {
                timer.cancel()
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    enum class Actions {
        START, STOP
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("ForeGround Service Notification").setContentText("test").build()


        timer = object : CountDownTimer(1000000, 5000) {
            override fun onTick(millisUntilFinished: Long) {
                Toast.makeText(this@RunningService, "water", Toast.LENGTH_SHORT).show()
            }

            override fun onFinish() {
                stopSelf()
            }
        }
        timer.start()

        startForeground(1,notification)
    }
}