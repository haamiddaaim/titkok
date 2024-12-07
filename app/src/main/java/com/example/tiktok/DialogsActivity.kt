package com.example.tiktok

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktok.adapter.ViewPagerAdapter
import com.example.tiktok.databinding.ActivityDialogsBinding

class DialogsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDialogsBinding
    lateinit var toggle: ActionBarDrawerToggle
    //action bar drawer toggle is used to have the toggle button in the toolbar to be able to open and close navigation drawer by clicking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this,binding.drawerlayout, R.string.open, R.string.close)
        binding.drawerlayout.addDrawerListener(toggle)
        //how to connect drawer with toggle


        toggle.syncState()
        //how to tell the toggle it should sink it's state which means it tells our toggle it's ready to be used
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //setDisplayHomeAsUpEnabled means when toggle is opened that it moves to a back arrow and when you click on back arrow nd will close again

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menuitem1 -> {
                    startActivity(Intent(this, NotificationsActivity::class.java))
                    Toast.makeText(applicationContext,"Item 1 clicked",Toast.LENGTH_SHORT).show()
                }
                R.id.menuitem2 -> {
                    startActivity(Intent(this, IntentServiceActivity::class.java))
                    Toast.makeText(applicationContext, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                }
                R.id.menuitem3 -> {
                    Toast.makeText(applicationContext,"Item 3 clicked",Toast.LENGTH_SHORT).show()
                }
                }
            true //means we handled the click

        }
        //how to respond to menu item clicks



        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add contact")
            .setMessage("Do you want to add Mr Popo to your contacts list?")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "You added Mr Popo to your contact list", Toast.LENGTH_SHORT)
                    .show()
                //we need the dialogInterface parameter if we want to access the interface of our dialog
                //when you use a fun like this that takes two parameters and we don't need the parameters we can replace them with _
                //press control and space to insert parameters dialogInterface, i -> for the lamda fun if you want to cancel the dialog without choosing yes or no
                //everytime you use the setpositive button it will dismiss the dialog automatically if you click on it
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(
                    this,
                    "You didn't added Mr Popo to your contact list",
                    Toast.LENGTH_SHORT
                ).show()
                //alertdialog contains an inner builder class which can only set the options for the dialog we want it to have
                //how to build a dialog with a yes or no option
            }
            .create()

        binding.dialog1.setOnClickListener {
            addContactDialog.show()
        }
        val options = arrayOf("First Item", "Second Item", "Third Item")
        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setSingleChoiceItems(options, 0) { _, i ->
                //checkeditems represents which item should be checked by default
                Toast.makeText(this, "You clicked on ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            //i is a parameter to the lamda fun is the index of the item that is currently checked
            //how to build a single choice dialog which is similar to radio buttons just in form of a dialogue where you have several options to choose from but you can only choose one
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "You accepted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                Toast.makeText(this, "You declined", Toast.LENGTH_SHORT).show()
            }.create()
        binding.dialog2.setOnClickListener {
            singleChoiceDialog.show()
        }
        val multipleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "You checked ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked ${options[i]}", Toast.LENGTH_SHORT).show()
                    //if we uncheck an item
                }
                //we only want to show the toast if we checked the option one...not when we uncheck it so we have to use if (isChecked)
                //booleanArray is used to determine the initial state of our options
                //false,false,false just means all items are unchecked by default
            }
            //i is a parameter to the lamda fun is the index of the item that is currently checked
            //how to build a single choice dialog which is similar to radio buttons just in form of a dialogue where you have several options to choose from but you can only choose one
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "You accepted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                Toast.makeText(this, "You declined", Toast.LENGTH_SHORT).show()
            }.create()
        binding.dialog3.setOnClickListener {
            multipleChoiceDialog.show()
        }
        binding.recyclerviewbutton.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
        binding.fragmentbutton.setOnClickListener {
            startActivity(Intent(this, FragmentsActivity::class.java))
        }
        binding.bottomnav.setOnClickListener {
            startActivity(Intent(this, BottomNavigationActivity::class.java))
        }
        binding.viewpager2.setOnClickListener {
            startActivity(Intent(this, ViewPager2Activity::class.java))
        }


        val customlist = listOf("First", "Second", "Third", "Fourth")
        //how to set the spinner items if you only know them at runtime
        val adapter = ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            customlist
        )
        binding.spmonths.adapter = adapter
        //R.layout.support_simple_spinner_dropdown_item is how to provide a resource that is the layout file for a spinner item but we don't have a custom one so we use the one already built in
        //how to define a adapter for the spinner that is used to adapt that list because every spinner item is a view
        //ArrayAdapter is a very simple adapter that will adapter simple strings
        binding.spmonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    this@DialogsActivity,
                    "You selected ${adapterView?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                //you can not pass this as context as this is an anonymous class and this refers to OnItemSelectedListener not to the mainactivity
                //this@DialogsActivity or application context tells kotlin that we mean the mainacivity inside the anonymous class
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        //how to create an anonymous class of the onitemselectedlistener
        //how to detect whenever the currently selected item changes
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //how to respond correctly to clicks on our toggle button and to clicks on the menu items in the navigation drawer
        if (toggle.onOptionsItemSelected(item)){
            //the user clicked on toggle button so we need to return true on this fun
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}