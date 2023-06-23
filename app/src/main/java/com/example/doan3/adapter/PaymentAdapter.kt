package com.example.doan3.adapter



import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doan3.databinding.ActivityPaymentItemBinding

import com.example.doan3.model.ShoppingCartModel

class PaymentAdapter(private val images: List<ShoppingCartModel>) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {
    // ...


    fun getItems(): List<ShoppingCartModel> {
        return images
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityPaymentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.bind(img)

    }
    override fun getItemCount(): Int {
        return images.size
    }


    inner class ViewHolder(val binding: ActivityPaymentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ShoppingCartModel) {

            binding.txtPayName.text = product.productName
            binding.txtPayPrice.text = product.price.toString()
            binding.txtPayQuantity.text = product.quantity.toString()

            Glide.with(binding.root)
                .load(product.image)
                .into(binding.txtPayImage)

            // Bind other views if needed
        }

    }


}

