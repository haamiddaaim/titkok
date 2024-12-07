package com.example.tiktok

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tiktok.adapter.TodoAdapter
import com.example.tiktok.databinding.ActivityDialogsBinding
import com.example.tiktok.databinding.ActivityRecyclerViewBinding
import com.example.tiktok.model.Todo

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var todoList = mutableListOf(
            Todo("Follow AndroidDevs", false),
            Todo("Learn about Recyclerview", true),
            Todo("Feed my cat", false),
            Todo("Prank my boss", false),
            Todo("Eat some curry", true),
            Todo("Ask my crush out", false),
            Todo("Take a shower", false)
            //how to create the todos list and create the adapter and pass this to the recyclerview
        )
        val adapter = TodoAdapter(todoList)
        //create an adapter that takes todoList as a parameter
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //LinearLayoutManager is the standard layout manager that is just to display items on the screen and set when a item should be recycled etc

        binding.btnaddtodo.setOnClickListener {
            val title = binding.edittext.text.toString()
            val todo = Todo(title, false)
            todoList.add(todo)
            //adds the newly created todos to our todolist but it will not update the recyclerview
            adapter.notifyItemInserted(todoList.size - 1)
            //how to update the recyclerview and insert the newly created todos into the last position
            //todoList.size - 1 because the index starts at 0 so we need to subtract 1
            // adapter.notifyDataSetChanged() will also work but don't use it because it will update the whole recyclerview since it doesn't know which entry it should updated so it's not efficient
            binding.edittext.text.clear()
        }
    }
}