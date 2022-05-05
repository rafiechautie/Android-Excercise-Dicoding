package com.example.mybroadcastreceiver

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class DownloadService: JobIntentService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            enqueueWork(this, this::class.java, 101, intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    //function on handlework akan dijalankan ketika tombol download ditekan
    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "Download Service dijalankan")
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        //ketika registeReceiver dijalankan maka seketika fungsi onReceive pada downloadreceiver akan dijalankan juga
        val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
        sendBroadcast(notifyFinishIntent)
    }

    companion object {
        val TAG: String = DownloadService::class.java.simpleName
    }
}