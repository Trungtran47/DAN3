package com.example.doan3.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doan3.activity.DetailProduct

import com.example.doan3.databinding.ActivitySearchItemBinding
import com.example.doan3.model.ProductModel
import java.util.*


class SearchAdapter(private var searchList: List<ProductModel>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    fun setData(data: List<ProductModel>) {
        searchList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ActivitySearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val product = searchList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProduct::class.java)
            intent.putExtra("productId", product.productId)
            intent.putExtra("productName", product.productName)
            intent.putExtra("product_desc", product.product_desc)
            intent.putExtra("price", product.price)
            intent.putExtra("imageUrl", product.image)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class SearchViewHolder(val binding: ActivitySearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.txtSearchName.text = product.productName
            binding.txtSearchPrice.text = product.price
            Glide.with(binding.root)
                .load(product.image)
                .into(binding.txtSearchImage)

            // Bind other views if needed
        }
    }
}







