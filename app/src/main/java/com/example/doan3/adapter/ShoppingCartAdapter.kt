package com.example.doan3.adapter


import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.doan3.activity.DetailProduct
import com.example.doan3.activity.ShoppingCart
import com.example.doan3.databinding.ActivityCartItemBinding

import com.example.doan3.model.ShoppingCartModel
import com.example.doan3.utils.Utils
import org.json.JSONObject


class ShoppingCartAdapter(private val images: List<ShoppingCartModel>) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {


//    private var totalPrice: Double = 0.0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.bind(img)
        calculateTotalPrice()
        updateTotalPrice()
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailProduct::class.java)
            intent.putExtra("productId", img.productId)
            intent.putExtra("productName", img.productName)

            intent.putExtra("price", img.price)
            intent.putExtra("imageUrl", img.image)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return images.size
    }

//    private var quantityChangeListener: QuantityChangeListener? = null
//    fun setQuantityChangeListener(listener: QuantityChangeListener) {
//        this.quantityChangeListener = listener
//    }

    fun calculateTotalPrice(): Int {
        var totalPrice = 0
        for (img in images) {
            totalPrice += (img.price * img.quantity)
        }
        return totalPrice

//        quantityChangeListener?.onQuantityChanged(totalPrice)
    }

    fun updateTotalPrice() {
        calculateTotalPrice()

//            quantityChangeListener?.onQuantityChanged(totalPrice)
    }

    inner class ViewHolder(val binding: ActivityCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shop: ShoppingCartModel) {
            binding.txtCarName.text = shop.productName
            binding.txtCarPrice.text = shop.price.toString()
            binding.txtCarQuantity.text = shop.quantity.toString()

            var isMinimumQuantity = false

            binding.btnCarCong.setOnClickListener {
                if (shop.quantity < 10) {
                    shop.quantity++
                    isMinimumQuantity = false
                    updateCartQuantity(shop.cartId, shop.quantity, itemView.context)
                    binding.txtCarQuantity.text = shop.quantity.toString()
                }
            }

            binding.btnCarTru.setOnClickListener {
                if (shop.quantity > 1 && !isMinimumQuantity) {
                    shop.quantity--
                    if (shop.quantity == 1) {
                        isMinimumQuantity = true
                    }
                    updateCartQuantity(shop.cartId, shop.quantity, itemView.context)
                    binding.txtCarQuantity.text = shop.quantity.toString()
                }
            }

            Glide.with(binding.root)
                .load(shop.image)
                .into(binding.txtCarImage)






                binding.btnDelete.setOnClickListener {
                    val productIdToDelete = images[position].cartId
                    val alertDialogBuilder = AlertDialog.Builder(itemView.context)
                    alertDialogBuilder.setTitle("Xóa sản phẩm")
                    alertDialogBuilder.setMessage("Bạn có chắc muốn xóa sản phẩm này?")
                    alertDialogBuilder.setPositiveButton("Đồng ý") { dialog, _ ->
                        // Gửi yêu cầu xóa sản phẩm đến máy chủ
                        val queue = Volley.newRequestQueue(itemView.context)
                        val url =
                            Utils.BASE_URL + "doan3/LOG/DeletePrCart.php?cartId=$productIdToDelete"
                        val deleteRequest = StringRequest(
                            Request.Method.GET, url,
                            { response ->
                                // Xóa sản phẩm khỏi danh sách sản phẩm hiển thị
                                images.toMutableList().removeAt(adapterPosition)
                                notifyDataSetChanged()
                                val intent = Intent(itemView.context, ShoppingCart::class.java)
                                itemView.context.startActivity(intent)
                            },
                            { error ->
                                Log.e(TAG, "Error deleting product: $error")
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

                    updateTotalPrice()
                }

            }
        }

      private   fun updateCartQuantity(cartId: Int, quantity: Int,context: Context) {

            val images = mutableListOf<ShoppingCartModel>()
            val queue = Volley.newRequestQueue(context)
          val url =  Utils.BASE_URL + "doan3/LOG/UpdatePrQuantity.php?cartId=$cartId&quantity=$quantity"

          val requestBody = HashMap<String, String>()
          requestBody["cartId"] = cartId.toString()
          requestBody["quantity"] = quantity.toString()


                val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->


                    // Xử lý phản hồi từ server sau khi cập nhật thành công
                    for (i in 0 until response.length()) {
                        val item = response.getJSONObject(i.toString())
                        val cartId = item.getInt("cartId")
                        val productId = item.getInt("productId")
                        val sId = item.getString("sId")
                        val name = item.getString("productName")
                        val price = item.getInt("price")
                        val quantity = item.getInt("quantity")


                        val imageUrl =
                            Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")
//
                        val image = ShoppingCartModel(cartId, productId, sId, imageUrl, name, price, quantity)
                        images.add(image)
                    }
                    // Sử dụng adapter cho RecyclerView thông qua biến binding

                },
                { error ->
                    // Xử lý lỗi khi gửi yêu cầu cập nhật số lượng sản phẩm
                })

            queue.add(request)
        }


    }






