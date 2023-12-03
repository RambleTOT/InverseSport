package ramble.sokol.inversesport.model.entity

import com.google.gson.annotations.SerializedName

data class GetCurrentEvent(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("platform")
    val platform: PlatformEntity?,
)
