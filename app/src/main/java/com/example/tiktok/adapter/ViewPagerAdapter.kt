package com.example.tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tiktok.databinding.ItemViewPagerBinding

class ViewPagerAdapter (
    val images: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder( val binding : ItemViewPagerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(binding)
    }
    override fun getItemCount(): Int {
       return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.binding.ivImage.setImageResource(curImage)
    }
    //how to set the images to our imageviews
    //we use a list of ints because we added our images to a drawable folder and we can get those images with their id and that is an int
}