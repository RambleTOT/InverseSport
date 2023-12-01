package ramble.sokol.inverseeducation.data.util

import com.google.gson.JsonObject
import ramble.sokol.inversesport.model.entity.GetTokenResponse
import ramble.sokol.inversesport.model.entity.UserLoginEntity
import ramble.sokol.inversesport.model.entity.UserSignupEntity
import ramble.sokol.inversesport.model.entity.UserSignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiMethod {

    @POST("users/auth/token/login/")
    fun getToken(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @POST("users/auth/users/")
    fun createAccount(@Body body: UserSignupEntity): Call<UserSignupResponse>

}