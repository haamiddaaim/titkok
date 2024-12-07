package com.example.tiktok

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "MyService"

    init {

        Log.d(TAG, "Service is running...")
    }

    override fun onBind(intent: Intent?): IBinder? = null
    //each service class needs to implement onbind but you probably won't need it often as it's a method for when you have mulitiple clients that want to connect to your service at the same time
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG,dataString)
        }
            //if we start the service for the first time then we won't attach any data string so dataString will be null. null check ?

      Thread{
          while (true){ }
      }.start()
        //how to run the service on a separate thread from the main one

        return START_STICKY
        //what you return will choose how the system handles it if it kills your service
        //START_NOT_STICKY means if android system kills your service then it won't recreate it if it has resources again
        //START_STICKY means if the service is killed by the system it will be recreated when possible and the last intent passed in this fun won't be delivered. intent is also be null
        //START_REDELIVER_INTENT will redeliver the intent

        //by overriding onStartCommand fun we can get the intent we started this service with and also attach data to that intent to communicate from our activity to our service
        //for example we can send commands and make our service do different things in different situations

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service is being killed")
    }

}