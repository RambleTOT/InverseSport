package ramble.sokol.inverseeducation.presentation.manager

import ramble.sokol.inverseeducation.data.util.ApiMethod
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        private val BASE_URL = "https://inverse-tracker.store/api/"

    }

    fun getApi() : ApiMethod{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMethod::class.java)
    }

}