package com.example.tiktok

import android.app.IntentService
import android.content.Intent
import android.util.Log


class MyIntentService : IntentService("MyIntentService") {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MyIntentService

        //this instance will hold the current instance of the service class and we will initialize this instance when this service class is created
        var isRunning = false


        fun stopService() {
            Log.d("MyIntentService", "Service is stopping...")
            isRunning = false
            instance.stopSelf()
        }
    }

    //how to stop the intent
    override fun onHandleIntent(po: Intent?) {
        //every intent service needs to implement this function which is used to process the intent we pass to the service when we start it
        try {
            isRunning = true
            //true because if the service goes into this fun we know that it is running
            //we will let our thread sleep here and that could throw an exception so make sure you surround it with try and catch block
        while (isRunning){
            Log.d("MyIntentService","Service is running...")
            Thread.sleep(1000)
        }
        }catch (e: InterruptedException){
            Thread.currentThread().interrupt()
        }
        //catch block because the sleep fun can't throw an interrupted exception


    }
}