package com.example.registratiohform

import Api.ApiClient
import Api.ApiInterface
import Models.LoginRequest
import Models.RegisterResponse
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.view.View
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        clickLogin()
    }
    fun clickLogin(){
        var error= false
        binding.btnLogin2.setOnClickListener {
            var email= binding.etEmail.text.toString()
            if(email.isEmpty()){
                error= true
                binding.etEmail.setError("This field required")
            }
            var password= binding.etPassword.text.toString()
            if(password.isEmpty()){
                error= true
                binding.etPassword.setError("This field required")
            }
            if (!error){
                binding.pbLogin.visibility= View.VISIBLE
                var lrgRequest= LoginRequest(
                  name=name,
                  Password= password
                )
                var retrofit= ApiClient.buildService(ApiInterface::class.java)
                var request= retrofit.loginStudent(lrgRequest)
                request.enqueue(object : Callback<RegisterResponse?> {
                    override fun onResponse(
                        call: Call<RegisterResponse?>,
                        response: Response<RegisterResponse?>
                    ) {
                        binding.pbLogin.visibility= View.VISIBLE
                    if (response.isSuccessful){
                    Toast.makeText(baseContext, "Login is successful", Toast.LENGTH_LONG).show()
                    }
                    else{
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                    }
                    }

                    override fun onFailure(call: Call<RegisterResponse?>, t: Throwable) {
                        binding.pbLogin.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

}

