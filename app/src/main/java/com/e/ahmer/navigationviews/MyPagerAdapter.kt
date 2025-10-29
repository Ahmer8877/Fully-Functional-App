package com.e.ahmer.navigationviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

@Suppress("LocalVariableName")
class MyPagerAdapter (private val images : List<Int>): RecyclerView.Adapter<MyPagerAdapter.ViewHolder>() {

    inner class ViewHolder(Itemview : View) : RecyclerView.ViewHolder(Itemview) {
        val pagerpic : ImageView=Itemview.findViewById(R.id.pagerPic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemviews= LayoutInflater.from(parent.context).inflate(R.layout.viepager,parent,false)
        return ViewHolder(itemviews)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pagerpic.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return images.size
    }

}