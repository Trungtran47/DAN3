package com.example.doan3.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doan3.activity.DetailProduct
import com.example.doan3.databinding.ActivityHaisanItemBinding

import com.example.doan3.model.ProductModel

class HaisanAdapter(private val images: List<ProductModel>) : RecyclerView.Adapter<HaisanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityHaisanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.bind(img)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProduct::class.java)
            intent.putExtra("productId", img.productId)
            intent.putExtra("productName", img.productName)
            intent.putExtra("product_desc", img.product_desc)
            intent.putExtra("price", img.price)
            intent.putExtra("imageUrl", img.image)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(private val binding: ActivityHaisanItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: ProductModel) {
            binding.nameHaisan.text = feature.productName
            binding.priceHaisan.text = feature.price
            Glide.with(binding.root)
                .load(feature.image)
                .into(binding.imageHaisan)
//            binding.imageHaisan.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailProduct::class.java)
//                intent.putExtra("productId", feature.price)
//                binding.root.context.startActivity(intent)
//            }

        }
    }



}
