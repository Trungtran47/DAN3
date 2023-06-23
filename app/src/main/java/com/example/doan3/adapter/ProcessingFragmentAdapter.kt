package com.example.doan3.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

import com.example.doan3.model.Order
import com.android.volley.Request
import com.example.doan3.R
import com.example.doan3.databinding.FragmentProcessingItemBinding
import com.example.doan3.utils.Utils

import org.json.JSONObject


class ProcessingFragmentAdapter(private val images: MutableList<Order>) : RecyclerView.Adapter<ProcessingFragmentAdapter.ViewHolder>() {
    // ...


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProcessingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = images[position]
        holder.bind(img)

    }
    override fun getItemCount(): Int {
        return images.size
    }


    inner class ViewHolder(val binding: FragmentProcessingItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Order) {
            binding.txtOrDetStatus.setOnClickListener {
                if (product.status == 1) {
                    product.status = 2
                    binding.txtOrDetStatus.text = "Đã nhận"
                    // Gửi yêu cầu cập nhật trạng thái đơn hàng lên server ở đây
                    updateOrderStatus(product.id, 2, binding.root.context)

                }
            }

            binding.txtOrDetName.text = product.productName
            binding.txtOrDetPrice.text = product.price.toString()
            binding.txtOrDetQuantity.text = product.quantity.toString()

            if (product.status == 0) {
                binding.txtOrDetStatus.text = "Đang xử lý"
                binding.txtOrDetStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorProcessing))
            } else if (product.status == 1) {
                binding.txtOrDetStatus.text = "Đang giao hàng"
                binding.txtOrDetStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorShipping))
            } else {
                // Trường hợp khác
                binding.txtOrDetStatus.text = "Đã nhận"
                binding.txtOrDetStatus.setTextColor(ContextCompat.getColor(binding.root.context, R.color.colorReceived))
            }

            Glide.with(binding.root)
                .load(product.image)
                .into(binding.txtOrDetImage)

            // Bind other views if needed
        }
        private fun updateOrderStatus(id: Int, status: Int,context: Context) {
            // Gửi yêu cầu cập nhật trạng thái đơn hàng lên server
            // Ví dụ: sử dụng Volley để gửi yêu cầu POST đến API updateOrderStatus
            val queue = Volley.newRequestQueue(context)
            val url =  Utils.BASE_URL + "doan3/LOG/UpdateOrderdetails.php?id=$id&status=2" // Thay thế bằng URL của API cập nhật trạng thái đơn hàng
            val requestBody = JSONObject()
            requestBody.put("id", id)
            requestBody.put("status", status)

            val request = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->

                    // Xử lý phản hồi từ server sau khi cập nhật thành công
                    for (i in 0 until response.length()) {
                        val item = response.getJSONObject(i.toString())
                        val id = item.getInt("id")
                        val productName = item.getString("productName")
                        val quantity = item.getInt("quantity")
                        val price = item.getInt("price")
                        val status = item.getInt("status")

                        val imageUrl =
                            Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")

                        val image = Order(id,productName, imageUrl, price, quantity, status)
                        if (image != null) {
                            images.add(image)
                        }

                    }
                },
                { error ->
                    // Xử lý lỗi khi gửi yêu cầu cập nhật trạng thái đơn hàng
                })

            queue.add(request)



        }


    }


}

