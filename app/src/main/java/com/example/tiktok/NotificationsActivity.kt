package com.example.tiktok

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.example.tiktok.databinding.ActivityNotificationsBinding

class NotificationsActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationsBinding
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    //globally define a channel id and name. channel id must be unique and is used to differentiate between several notification channels
    // private val PERMISSION_REQUEST_CODE = 1001 // Unique integer to identify permission request
    //in notification channel we will post our notifications and this channel will hold the configuration we want for notifications
    //  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        val intent = Intent(this, Androidfund::class.java)

        val pendingIntent = PendingIntent.getActivity(applicationContext, NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE).run {
           // addNextIntentWithParentStack(intent)
            //addNextIntentWithParentStack will add this activity that we will open on that notification click. this will add it to our activity back stack
            //we are able to click on the back button when we open this activity from the notification and still be able to go back to previous activities
           // getPendingIntent(0, )
            //FLAG_UPDATE_CURRENT means that when the pending intent already exists...we want to keep it and update its data with our new data
            //pending intents allows another app to execute a piece of code from our app
        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Awesome notification")
            .setContentText("This is the content text")
            .setSmallIcon(R.drawable.ic_star)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            //.setContentIntent(pendingIntent)
            .build()
        //how to create our notification that we want to post in the channel


        val notificationManager = NotificationManagerCompat.from(this)

        binding.button2.setOnClickListener {
            // Check if the app has the required permission
            if (ActivityCompat.checkSelfPermission(
                    this,
                    POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // If the permission is not granted, request it
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(POST_NOTIFICATIONS),
                        NOTIFICATION_ID
                    )
                }
                return@setOnClickListener
            }
            // If the permission is granted, show the notification
            notificationManager.notify(NOTIFICATION_ID, notification)

        }
    }

    private fun createNotificationChannel() {
        //before android oreo you didn't need to create a notification channel but after we need to so we have to check if the app is running on android oreo or later
        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            lightColor = Color.RED
            enableLights(true)
            //how to change the LED light color when the notification shows up
            //how to do something with the channel you just created

            //IMPORTANCE_DEFAULT will show a notification without any sound effects...if you want sound effects choose higher importance
            //depending on which importance level you would choose for your channel it will change whether your notification is shown as a heads up or if it comes with a sound effect
        }
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        //as NotificationManager since manager is an any object
        //how to create a notification manager that will create notification channel
    }
}