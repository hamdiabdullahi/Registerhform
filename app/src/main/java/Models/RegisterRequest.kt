package Models

data class RegisterRequest(
    var name: String,

//    @Serial("phone_number")
    var PhoneNumber:String,
    var email: String,
//    @SerializedName("date_of_birth")
    var dateOfBirth: String,
    var nationality: String,
    var password: String
    )
