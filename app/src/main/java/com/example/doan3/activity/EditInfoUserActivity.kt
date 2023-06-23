package com.example.doan3.activity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

import com.example.doan3.databinding.ActivityEditInfoUserBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.doan3.utils.Utils

import org.json.JSONObject


class EditInfoUserActivity : AppCompatActivity() {
    private var binding: ActivityEditInfoUserBinding? = null
    private lateinit var profil: SharedPreferences
    private var imageUri: Uri? = null
    private val REQUEST_TAG = "updateRequest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditInfoUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        profil = getSharedPreferences("login_session", MODE_PRIVATE)
        val name = intent.getStringExtra("name")
        val zipcode = intent.getStringExtra("zipcode")
        val country = intent.getStringExtra("country")
        val city = intent.getStringExtra("city")
        val address = intent.getStringExtra("address")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")

        binding?.textId?.text = Editable.Factory.getInstance().newEditable(profil.getInt("id", 0).toString())
       binding?.txtName?.text = Editable.Factory.getInstance().newEditable(name)
        binding?.textPhone?.text = Editable.Factory.getInstance().newEditable(phone)
        binding?.textAddress?.text = Editable.Factory.getInstance().newEditable(address)
        binding?.textEmail?.text = Editable.Factory.getInstance().newEditable(email)
        binding?.textViewCountry?.text = Editable.Factory.getInstance().newEditable(country)
        binding?.textViewCity?.text = Editable.Factory.getInstance().newEditable(city)
        binding?.textZipcode?.text = Editable.Factory.getInstance().newEditable(zipcode)

        binding?.buttonSave?.setOnClickListener {
            val id = binding?.textId?.text.toString()
            val name = binding?.txtName?.text.toString()
            val zipcode = binding?.textZipcode?.text.toString()
            val country = binding?.textViewCountry?.text.toString()
            val city = binding?.textViewCity?.text.toString()
            val address = binding?.textAddress?.text.toString()
            val email = binding?.textEmail?.text.toString()
            val phone = binding?.textPhone?.text.toString()

            if (name.isEmpty() || zipcode.isEmpty() || country.isEmpty() || city.isEmpty() || address.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(
                    this@EditInfoUserActivity,
                    "Vui lòng điền đầy đủ thông tin",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!isValidEmail(email)) {
                Toast.makeText(
                    this@EditInfoUserActivity,
                    "Email không hợp lệ",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                updateInfo(id,name,zipcode, country, city, address, email, phone)
            }
            val intent = Intent(this, InfoUserActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }



//        val url = "http://192.168.1.84/doan3/LOG/UpdateInfoUser.php?id=$id&name=$name&zipcode=$zipcode&country=$country&city=$city&address=$address&email=$email&phone=$phone" // Thay thế bằng URL của API cập nhật thông tin người dùng

    private fun updateInfo(id: String, name: String, zipcode: String, country: String, city: String, address: String, email: String, phone: String) {
        val queue = Volley.newRequestQueue(this@EditInfoUserActivity)
        Log.d("UpdateInfoUser", "Name: $name")
        Log.d("UpdateInfoUser", "Zipcode: $zipcode")
        Log.d("UpdateInfoUser", "Country: $country")
        Log.d("UpdateInfoUser", "City: $city")
        Log.d("UpdateInfoUser", "Address: $address")
        Log.d("UpdateInfoUser", "Email: $email")
        Log.d("UpdateInfoUser", "Phone: $phone")

        val url =  Utils.BASE_URL + "doan3/LOG/UpdateInfoUser.php?id=$id&name=$name&zipcode=$zipcode&country=$country&city=$city&address=$address&email=$email&phone=$phone"

        val params = HashMap<String, String>()
        params["name"] = name
        params["zipcode"] = zipcode
        params["country"] = country
        params["city"] = city
        params["address"] = address
        params["email"] = email
        params["phone"] = phone

        val request = object : StringRequest(Method.GET, url,
            Response.Listener<String> { response ->
                // Xử lý khi cập nhật thành công
                Toast.makeText(
                    this@EditInfoUserActivity,
                    "Cập nhật thành công",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            },
            Response.ErrorListener { error ->
                // Xử lý lỗi khi gửi yêu cầu cập nhật
                Toast.makeText(
                    this@EditInfoUserActivity,
                    "Cập nhật thất bại",
                    Toast.LENGTH_LONG
                ).show()
            }) {
            override fun getParams(): Map<String, String> {
                return params
            }
        }

        request.tag = REQUEST_TAG // Gán thẻ cho yêu cầu

        queue.add(request)
    }




    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun BackHome(view: View) {
        startActivity(Intent(this@EditInfoUserActivity, HomeActivity::class.java))
    }

    fun choose(view: View) {
        pickImageGallery()
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityResultLauncher.launch(intent) // load ảnh lên
    }

    private var galleryActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult> { result ->
            //get url of image
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                imageUri = data!!.data

                //set to image view
                binding?.avatar?.setImageURI(imageUri)
            } else {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    )

    fun edit(view: View) {
        startActivity(Intent(this@EditInfoUserActivity, EditInfoUserActivity::class.java))
    }

    fun profile(view: View) {
        startActivity(Intent(this@EditInfoUserActivity, InfoUserActivity::class.java))
    }
}
