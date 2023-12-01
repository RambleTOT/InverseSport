package ramble.sokol.inversesport.model.entity

import com.google.gson.annotations.SerializedName

data class UserSignupResponse(
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("role")
    val role: Int?,
)
