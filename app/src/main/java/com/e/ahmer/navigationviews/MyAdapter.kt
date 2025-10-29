package com.e.ahmer.navigationviews

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(var context: Activity, var productlist: List<Product>) : RecyclerView.Adapter<MyAdapter.MyViweHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViweHolder {
        val items= LayoutInflater.from(context).inflate(R.layout.tweeterui,parent,false)
        return MyViweHolder(items)
    }

    override fun onBindViewHolder(holder: MyViweHolder, position: Int) {
        val currentItem=productlist[position]
        holder.itemName.text=currentItem.title
        holder.itemprice.text=currentItem.price.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.profile)

    }

    override fun getItemCount(): Int {
        return productlist.size
    }

    class MyViweHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        var profile : CircleImageView
        var itemName : TextView
        var itemprice : TextView
        init {
            profile=itemview.findViewById(R.id.TweeterImage)
            itemName=itemview.findViewById(R.id.Tweeternametext)
            itemprice=itemview.findViewById(R.id.tweeterContent)
        }

    }
}