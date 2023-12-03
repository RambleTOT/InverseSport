package ramble.sokol.inversesport.model.util

import com.google.gson.JsonObject
import ramble.sokol.inversesport.model.entity.GetAllEvents
import ramble.sokol.inversesport.model.entity.GetTokenResponse
import ramble.sokol.inversesport.model.entity.UserAccountResponse
import ramble.sokol.inversesport.model.entity.UserLoginEntity
import ramble.sokol.inversesport.model.entity.UserSignupEntity
import ramble.sokol.inversesport.model.entity.UserSignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiMethod {

    @POST("users/auth/token/login/")
    fun getToken(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @POST("users/auth/users/")
    fun createAccount(@Body body: UserSignupEntity): Call<UserSignupResponse>

    @GET("users/auth/users/me/")
    fun getMyAccount(@Header("Authorization") token: String): Call<UserAccountResponse>

    @GET("events/")
    fun getAllEvents(@Header("Authorization") token: String): Call<List<GetAllEvents>>

}