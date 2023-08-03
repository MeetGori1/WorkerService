package com.example.WorkerService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.myLooper()!!).postDelayed({
            startWorker()
        },1000)

        Handler(Looper.myLooper()!!).postDelayed({
            startRunningService()
        },5000)
    }

    private fun startWorker() {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
           val workRequest = PeriodicWorkRequest.Builder(BackgroundWorker::class.java, 15, TimeUnit.MINUTES, 1, TimeUnit.MINUTES).setConstraints(constraints).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    fun startRunningService(){
        val intent=Intent(this,RunningService::class.java)
        intent.action=RunningService.Actions.START.toString()
        startService(intent)
    }
}