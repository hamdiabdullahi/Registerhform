package Api

object ApiClient {
   var retrofit =Retrofit.builder()
       .baseUrl("http://13.244.243.129")
       .addConverterFactory(GsonConverterFactory.create())
       .build()

    fun <T> buildService(apiInterface: Class<T>):T{
        return retrofit.create(apiInterface)
    }
}