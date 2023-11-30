package ramble.sokol.inverseeducation.data.util

import ramble.sokol.inversesport.model.entity.GetTokenResponse
import ramble.sokol.inversesport.model.entity.UserLoginEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiMethod {

    @POST("users/auth/token/login/")
    fun getToken(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

}