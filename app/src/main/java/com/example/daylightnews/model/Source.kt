package com.example.daylightnews.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    @SerializedName("id")
    val id: Any? = null,
    @SerializedName("name")
    val name: String? = null
):Serializable