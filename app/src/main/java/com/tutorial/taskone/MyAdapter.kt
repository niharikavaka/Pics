package com.tutorial.taskone

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Context, val urlList:ArrayList<String>, val titleL:ArrayList<String>,):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){
      //  val userId: TextView =itemView.findViewById(R.id.textView)
        val title: TextView =itemView.findViewById(R.id.textView)
        val image:ImageView=itemView.findViewById(R.id.imageView)



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var itemView= LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // val currentItem=userList[position]

       // holder.userId.text=userList[position].id.toString()
        //   holder.title.text=userList[position].title
        holder.title.text= titleL[position]
        Picasso.get().load(urlList[position]).into(holder.image)
        // Log.d("navya", userList[position].title)
        Log.d("navya", urlList[position])
        Log.d("navya", titleL[position])


    }

    override fun getItemCount(): Int {
        return titleL.size

    }


}