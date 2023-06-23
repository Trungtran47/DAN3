package com.example.doan3.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.R
import com.example.doan3.adapter.ProcessingFragmentAdapter
import com.example.doan3.databinding.FragmentDeliveringBinding

import com.example.doan3.model.Order
import com.example.doan3.utils.Utils

class DeliveringFragment : Fragment() {
    private var _binding: FragmentDeliveringBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProcessingFragmentAdapter
    private lateinit var profil: SharedPreferences
    private val images = mutableListOf<Order>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentDeliveringBinding.inflate(inflater, container, false)
        binding.OrDetDelivering.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProcessingFragmentAdapter(images)
        binding.OrDetDelivering.adapter = adapter
        profil = requireActivity().getSharedPreferences("login_session", AppCompatActivity.MODE_PRIVATE)


        val queue = Volley.newRequestQueue(requireContext())
        val customer_id = profil.getInt("id",0).toString()
        val url =  Utils.BASE_URL + "doan3/LOG/OrderdetailsDeli.php?customer_id=$customer_id" // Thay thế bằng URL của máy chủ MySQL của bạn

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    val id = item.getInt("id")
                    val productName = item.getString("productName")
                    val quantity = item.getInt("quantity")
                    val price = item.getInt("price")
                    val status = item.getInt("status")


                    val imageUrl =
                    Utils.BASE_URL + "WEBTH/doan2/web/admin/uploads/" + item.getString("image")

                    val image = Order(id, productName, imageUrl, price, quantity, status)
                    if (image != null) {
                        images.add(image)
                    }

                }
                adapter.notifyDataSetChanged()

            },
            { error ->
                val message = when (error) {
                    is NoConnectionError -> "No internet connection"
                    is TimeoutError -> "Request timed out"
                    is AuthFailureError -> "Authentication failure"
                    is ServerError -> "Server error"
                    is NetworkError -> "Network error"
                    is ParseError -> "Data parsing error"
                    else -> "Unknown error"
                }
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            })

        queue.add(jsonArrayRequest)


        // Thêm yêu cầu vào hàng đợi của Volley



        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}