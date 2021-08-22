package com.example.registratiohform

import Api.ApiClient
import Api.ApiInterface
import Models.RegisterRequest
import Models.RegisterResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import java.util.jar.Attributes

lateinit var binding:ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        clickRegister()
    }
    fun setupSpinner() {
        var nationalities= arrayOf("select Nationality","Kenyan","Rwandan","South Sudanese","Ugandan")
        var nationalityadapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
    }

    fun clickRegister() {
        binding.btnNext.setOnClickListener {
            var intent= Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
        }
        var error = false
        binding.btnLoginPrvs.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field required")
            }
            val dob =binding.etDob.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDob.setError("this field required")
            }
            var spNationality=""
            if (binding.spNationality.selectedItemPosition!=0){
                spNationality = binding.spNationality.selectedItem.toString()
            }
            else{
                error = true
                Toast.makeText(baseContext,"select Nationality", Toast.LENGTH_LONG).show()
            }
            val phoneNumber = binding.etPhone.text.toString()
            if (phoneNumber.isEmpty()) {
                error = true
                binding.etPhone.setError("this field required")
            }
            val email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.setError("this field required")
            }
            binding.etDob.setError("this field required")
        }
        var nationality=""
        if (binding.spNationality.selectedItemPosition!=0){
            nationality = binding.spNationality.selectedItem.toString()
        }
        else{
            error = true
            Toast.makeText(baseContext,"select Nationality", Toast.LENGTH_LONG).show()
        }
        var phoneNumber = binding.etPhone.text.toString()
        if (phoneNumber.isEmpty()) {
            error = true
            binding.etPhone.setError("this field required")
        }
        var email = binding.etEmail.text.toString()
        if (email.isEmpty()) {
            error = true
            binding.etEmail.setError("this field required")
        }
        var password= binding.etPassword.text.toString()
        if (password.isEmpty()) {
            error = true
            binding.etPassword.setError("this field required")
        }

        if (!error){
            binding.pbRegistration.visibility=View.VISIBLE
            var rgRequest= RegisterRequest(
                name=name,
                PhoneNumber = phoneNumber,
                email = email,
                dateOfBirth = Dob,
                nationality = nationality,
                password = password
            )

            var retrofit= ApiClient.buildService(ApiInterface::class.java)
            var request= retrofit.registerStudent(rgRequest)
            request.enqueue(object : Callback<RegisterRequest> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    binding.pbRegistration.visibility=View.GONE
                    if (response.isSuccessful){
                        Toast.makeText(baseContext,"Registration successfully", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    binding.pbRegistration.visibility=View.GONE
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
 }
}