package com.example.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

//servide ini dijalankan secara asynchronous tapi tetep berada di main thread
class MyService : Service() {

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    //ketika button start service ditekan maka fungsi onStartCommand akan dijalankan
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service dijalankan...")
        // menghentikan service setelah 3 detik
        serviceScope.launch {
            delay(3000)
            stopSelf() //berfungsi untuk memberhentikan atau  mematikan service dari sistem android
            Log.d(TAG, "Service dihentikan")
        }
        //start_sticky berfungsi untuk menciptakan kembali service ketika service dimatikan oleh sistem android
        //karena kekurangan memori
        return START_STICKY
    }

    //fungsi onDestory akan dipanggil ketika service diberhentikan
    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy: ")
    }
}