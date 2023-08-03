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
import com.example.WorkerService.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            startWorker()
        }, 1000)

        /* Handler(Looper.myLooper()!!).postDelayed({
             startRunningService()
         },5000)
 */

        binding.btnStartService.setOnClickListener {
            startRunningService()
        }

        binding.btnStopService.setOnClickListener {
            stopRunningService()
        }
    }

    private fun startWorker() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(
            BackgroundWorker::class.java,
            15,
            TimeUnit.MINUTES,
            1,
            TimeUnit.MINUTES
        ).setConstraints(constraints).build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    fun startRunningService() {
        val intent = Intent(this, RunningService::class.java)
        intent.action = RunningService.Actions.START.toString()
        startService(intent)
    }

    fun stopRunningService() {
        val intent = Intent(this, RunningService::class.java)
        intent.action = RunningService.Actions.STOP.toString()
        startService(intent)
    }
}