package ramble.sokol.inversesport.model.entity

import com.google.gson.annotations.SerializedName

data class PlatformEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("address")
    val address: String?,
)
