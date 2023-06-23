package com.example.doan3.adapter

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.doan3.activity.DetailProduct
import com.example.doan3.activity.FavouriteActivity
import com.example.doan3.activity.ShoppingCart
import com.example.doan3.databinding.ActivityFavouriteItemBinding

import com.example.doan3.model.ProductModel
import com.example.doan3.utils.Utils

class FavouriteAdapter(private val images: List<ProductModel>) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityFavouriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ViewHolder(private val binding: ActivityFavouriteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feature: ProductModel) {
            binding.txtFavName.text = feature.productName
            binding.txtFavPrice.text = feature.price
            Glide.with(binding.root)
                .load(feature.image)
                .into(binding.txtFavImage)
//            binding.imageMonA.setOnClickListener {
//                val intent = Intent(binding.root.context, DetailProduct::class.java)
//                intent.putExtra("productId", feature.price)
//                binding.root.context.startActivity(intent)
//            }
            binding.btnDelete.setOnClickListener {
                val productIdToDelete = images[position].productId
                val alertDialogBuilder = AlertDialog.Builder(itemView.context)
                alertDialogBuilder.setTitle("Xóa sản phẩm")
                alertDialogBuilder.setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                alertDialogBuilder.setPositiveButton("Đồng ý") { dialog, _ ->
                    // Gửi yêu cầu xóa sản phẩm đến máy chủ
                    val queue = Volley.newRequestQueue(itemView.context)
                    val url =
                        Utils.BASE_URL + "doan3/LOG/DeleteWishlist.php?productId=$productIdToDelete"
                    val deleteRequest = StringRequest(
                        Request.Method.GET, url,
                        { response ->
                            // Xóa sản phẩm khỏi danh sách sản phẩm hiển thị
                            images.toMutableList().removeAt(adapterPosition)
                            notifyDataSetChanged()
                            val intent = Intent(itemView.context, FavouriteActivity::class.java)
                            itemView.context.startActivity(intent)
                        },
                        { error ->
                            Log.e(ContentValues.TAG, "Error deleting product: $error")
                        }
                    )




                    queue.add(deleteRequest)
                    dialog.dismiss()
                }
                alertDialogBuilder.setNegativeButton("Hủy bỏ") { dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()


            }
        }

    }




}
