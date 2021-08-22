package Models

data class LoginResponse( var name: String,
//                          @SerializedName("phone_number")
                          var PhoneNumber:String,
                          var email: String,
//                          @SerializedName("date_of_birth")
                          var dateOfBirth: String,
                          var nationality: String,
//                          @SerializedName("student_id")
                          var studentId: String)
