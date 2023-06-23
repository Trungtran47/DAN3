package com.example.doan3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doan3.R
import com.example.doan3.activity.HomeActivity
import com.example.doan3.model.HomeModel


class HomeAdapter(val homeList: List<HomeModel>, val listener: HomeActivity) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val homeitem = layoutInflater.inflate(R.layout.home_horizontal_item,parent,false)
        return HomeHolder(homeitem)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val home = homeList[position]
        holder.imageView.setImageResource(home.image)
        holder.textView.text = home.name
    }

    override fun getItemCount(): Int {
        return homeList.size
    }
    interface MyClickListenFeature{
        fun onClick(position: Int)
    }

    inner class HomeHolder(val view: View): RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.hor_img)
        val textView = view.findViewById<TextView>(R.id.hor_text)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                listener.onClickHome(position)
            }
        }
    }
}