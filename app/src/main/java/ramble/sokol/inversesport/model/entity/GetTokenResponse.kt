package ramble.sokol.inversesport.model.entity

import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("auth_token")
    val token: String?,
)
