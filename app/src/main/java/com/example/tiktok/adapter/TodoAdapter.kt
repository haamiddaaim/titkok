package com.example.tiktok.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiktok.R
import com.example.tiktok.databinding.ItemTodoBinding
import com.example.tiktok.model.Todo

class TodoAdapter (
    var todos: List<Todo>
    //our itemtodo adapters job now is to take that list and set the date of that list to the corresponding items of your recyclerview
) : RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>()
//how to define TodoAdapter as a RecyclerView Adapter by inheriting from RecyclerView.Adapter

//how to pass a list of our itemtodo items to the constructor


{
    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    // inner class TodoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    //we want to pass the view to our current item with itemView : View
    //TodoViewHolder needs to inherit from RecyclerView.ViewHolder
    //this adapter needs to know which data to set to which item and to tell the adapter that...we need to create another class that describes how a itemtodo of our list looks like
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_view, parent, false
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
        //if we set it to true it would automatically attach that layout that we inflate to the parent root view but for a recyclerview we have to set it to false so it won't crash

        //you have to inflate the item_todo xml file to get it as a view we can access in our kotlin file
        //we can use parent.context because we have the parent parameter which is view group which is the parent layout..we can get the context from it

        //in this function this is how you create a layout for a specific item of our recyclerview
//onCreateViewHolder is called when the recyclerview needs a new viewholder so if the user scrolls a little bit so that another item was recycled and it now needs to create a new item that is visible
//return ToDoViewHolder(view)
        return ToDoViewHolder(binding)
        //we need to return view holder that contains our just created view and pass in our view
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.apply {
//using apply means we don't need to write holder.binding all the time and can directly refer to the id
            todos[position].apply{
            tvTitle.text = title
            cbDone.isChecked = isChecked}
            //todos list at the index of position
            //we can access the corresponding item in our todos list and get it's title and set it to tvTitle text...same with checkbox
            //onBindViewHolder will be called for each item in our recyclerview so for the first item...it will called with the postion = 0

        }
        //binding is the view that contains all of our single views inside of our itemtodo xml file

        //we need the holder parameter to access the views inside of that viewholder so the textview and checkbox
        //position parameter is the current position of current index of the view that we are binding and we need to access ourto do list
        //how you bind the data to our items so it will be used to take the data from thetodo list and set it to the textview text, text width, title textview, and check state of checkbox

    }

    override fun getItemCount(): Int {
        //getItemCount returns how many items we have in our recyclerview
        return todos.size
        //return the size of the list
    }
}