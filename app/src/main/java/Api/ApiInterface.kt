package Api

import Models.LoginRequest
import Models.RegisterRequest
import Models.RegisterResponse
import android.telecom.Call

interface ApiInterface {
    @POST("/students/register")
fun registerStudent(@Body registrationRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/students/login")
    fun loginStudent(@Body registrationRequest: LoginRequest): Call<RegisterResponse>

}