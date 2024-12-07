package com.example.tiktok

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.tiktok.databinding.AndroidfundamentalsBinding

class Androidfund : AppCompatActivity() {
    lateinit var binding: AndroidfundamentalsBinding
    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            uri: Uri? -> binding.imageView3.setImageURI(uri) }
    //use registerForActivityResult when you expect a result for the activity (in this case Gallery app) we want to start instead of using startActivity
    //the path to an image or any kind of content is an Uri
//startactivity is deprecated and this is the new way of how to request an image
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AndroidfundamentalsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(findViewById(R.id.imageView3))

        val sharepref = getSharedPreferences("mypref", MODE_PRIVATE)
        val editor = sharepref.edit()

        binding.save.setOnClickListener {
            val name = binding.etname.text.toString()
            val age = binding.etage.text.toString().toInt()
            val adult = binding.checkBox.isChecked

            editor.apply {
                putString("name", name)
                putInt("age", age)
                putBoolean("isAdult", adult)
                apply()
                //apply() when that is all the data you want to put in
            }
            }
            binding.load.setOnClickListener {
                val name = sharepref.getString("name",null)
                //if the string name doesn't exist...it will be null
                val age = sharepref.getInt("age",0)
                val adult = sharepref.getBoolean("isAdult",false)

                binding.etname.setText(name)
                binding.etage.setText(age.toString())
                binding.checkBox.isChecked = adult


        }

        binding.dialogbutton.setOnClickListener {
            startActivity(Intent(this, DialogsActivity::class.java))
        }

        binding.buttonclick.setOnClickListener {

            getImage.launch("image*/")


            //ACTION_GET_CONTENT gets content from device
            // explicit intent  Intent(this, SecondActivity::class.java)
        }
        requestPermissions()
        binding.btnorder.setOnClickListener {


            val name = binding.etname.text.toString()
            val age = binding.etage.text.toString().toInt()
            val country = binding.etcountry.text.toString()
            val person = Person(name, age, country)
//a better way of passing data between activities with a data class
            Intent(this, SecondActivity::class.java).also {
                it.putExtra("EXTRA_PERSON", person)
                //we only need to put the person class into extra now
                /* it.putExtra("EXTRA_NAME",name)
                 it.putExtra("EXTRA_AGE",name)
                 it.putExtra("EXTRA_Country",name) */
//extras are used to pass date between activities


                //also is a scope function of kotlin
                //we can refer with it to the created intent
                startActivity(it)
            }
            //an intent is an intention to do an action like start a new activity
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(R.layout.custom_toast, null)
                show()
                //how to inflate the current view with layoutinflater
            }
            // 111111111111111111111111111111111111111111111111
            //create a instance with custom toast
            //how to create a custom Toast and show how layoutinflater works
            //Toast.makeText(applicationContext, "Hi, I am a toast:",Toast.LENGTH_SHORT).show()
            //when showing a toast it doesn't matter which context you choose
            //the activity context only lives as long as the activity does so if you want to live as long as the app use application context
            //if you do not do the note above it could create a memory leak as the act context will still be needed even though the activity is destroyed but it won't be since the context is still needed
            //since this is the inside an activity class you pass this as the context
            //makeText(applicationContext) is the context that refers to the whole application and isn't affected by activity lifecycle
            //maketext : charsequence needs access the layoutinflater
            //layoutinflater is used to create a view with a layout file from resources
            //context is a superclass of classes such as Activity and Application
            //Activity and Application can be passed as context to functions
            //you use context to get the already established files
            //charsequence is string
            //resid can be used to access the string resources
            val checkedMeatRadioButtonId = binding.rgmeat.checkedRadioButtonId
            //getting the currently checked id from the radio group by saving it in a variable
            val meat = findViewById<RadioButton>(checkedMeatRadioButtonId)
            val cheese = binding.cbcheese.isChecked
            val onions = binding.cbonions.isChecked
            val salad = binding.cbsalad.isChecked
            //by calling the ischecked variable we can get the current state of the checkbox to see if it's checked or not
            val orderString = "You ordered a burger with:\n" +
                    "${meat.text}" +
                    (if (cheese) "\nCheese" else "") +
                    (if (onions) "\nOnions" else "") +
                    (if (salad) "\nSalad" else "")
            binding.tvorder.text = orderString
            //\n is how you line break


        }
        /*  1
          2
          3
          4
          5
          6*/
        //string manipulation on how to create an instance using increment/decrement

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
        //instead of returning super.onCreateOptionsMenu(menu) we want to return true as it means we inflated our own menu
        // how to tell this activity that we want to implement the menu for the toolbar in this activity
        //onCreateOptionsMenu is called when the menu is made and if we override it we can specify our own menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //how to check which item was clicked and does the action event
            R.id.menuitemAddContact -> Toast.makeText(this, "You clicked on Add Contact",Toast.LENGTH_SHORT).show()
            R.id.menuitemFavorites -> Toast.makeText(this, "You clicked on Favorites",Toast.LENGTH_SHORT).show()
            R.id.menuitemSettings -> Toast.makeText(this, "You clicked on Settings",Toast.LENGTH_SHORT).show()
            R.id.menuitemClose -> finish()
            R.id.menuitemFeedback -> Toast.makeText(this, "You clicked on Feedback",Toast.LENGTH_SHORT).show()

        }

        return true
        //true means that we clicked on an item and we handled the event
    }
    //onOptionsItemSelected is how to detect onclick events on our menu items

    private fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED

    //== checks if it's equal to
    //this returns an int that says this permission was accepted or not accepted
    //can refer to all available permissions by writing manifest
    //ActivityCompat checks if the user accepted the permissions already
    private fun hasLocationForegroundPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    private fun hasLocationBackgroundPermission() = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_BACKGROUND_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
    //if you are using a real app, you should check if the user uses android q. otherwise you don't want to check this permission and it may crash without a check

    private fun requestPermissions() {

        var permissionsToRequest = mutableListOf<String>()
        //whenever you want to request several permissions always need to define the permissions as an string array because all the manifest permission types are strings
        //checks if all the permissions have been accepted
        //this list contains all the permissions the user hasn't accepted before or the permissions he accepted before but then revoked them again
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        }
        if (!hasLocationForegroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)

        }
        if (!hasLocationBackgroundPermission() && hasLocationForegroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        }
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
            //permissionsToRequest.toTypedArray() is how you take a mutable list and convert it to a string array
            //checks if the permissions are empty and if it is not we want to request all the permissions in the list
        }
    }

    //how to check if whether the user accepted those permissions above before and if not add each permission string to the request
    //when you want to request several permissions you have to use an string array since they are strings
    override fun onRequestPermissionsResult(
        requestCode: Int,
        //requestCode is the same as above which is 0 and is used to differentiate between several requested permissions
        permissions: Array<out String>,
        grantResults: IntArray
        //grantResults: IntArray represents the array that contains those package manager permission granted int
        //you can loop through grantResults and check if a particular permission was granted or not
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //you can ignore this super implementation since it just means this fun will call the implementation of that fun from the superclass activity
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            //if it equals to 0
            for (i in grantResults.indices)
            //grantResults.indices means it will start at 0 and go through results until it's length is minus 1 so it's the same as grantResults.size - 1
            //requestCode == 0 will check which permissions were accepted by the user and which were not
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionsRequest", "${permissions[i]} granted.")
                    //we looped through the grantResults array and checked for each grant result if it is equal to permission granted so if that particular permission was granted
                    //if it is granted then we know permissions[i] is at the index of i in the permissions: Array<out String>, then we print the permission was "granted"
                }
        }
    }
//fun that is called when the user accepts or declines all the permissions and then check if he accepted a particular permission or not
    //you can do this by overriding a fun inside of the activity class

    //shortcut onpermi to get the onRequestPermissionsResult fun faster
}




