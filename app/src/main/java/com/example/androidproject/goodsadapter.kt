package com.example.androidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidproject.ui.services.ItemsViewModel

class goodsadapter(val goodslist: ArrayList<ItemsViewModel>) :
    RecyclerView.Adapter<goodsadapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_look, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = goodslist[position]

        holder.disgoodname.text = currentEmp.goodsname
        Glide.with(holder.itemView.context) // Use the context from the ViewHolder
            .load(currentEmp.goodimage)
            .into(holder.disgooodimage)
    }


    override fun getItemCount(): Int {
        return goodslist.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val disgoodname : TextView = itemView.findViewById(R.id.disgoodsname)
        val disgooodimage: ImageView = itemView.findViewById(R.id.disgoodimage) // Initialize the ImageView


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}

