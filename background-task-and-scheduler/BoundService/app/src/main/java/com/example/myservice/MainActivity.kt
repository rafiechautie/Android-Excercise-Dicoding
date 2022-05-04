package com.example.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener(this)
        binding.btnStartJobIntentService.setOnClickListener(this)
        binding.btnStartBoundService.setOnClickListener(this)
        binding.btnStopBoundService.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_start_service){
            //kode untuk menjalankan service
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
        }else if(view.id == R.id.btn_start_job_intent_service){
            val mStartIntentService = Intent(this, MyJobIntentService::class.java)
            mStartIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
            MyJobIntentService.enqueueWork(this, mStartIntentService)
        }
    }
}