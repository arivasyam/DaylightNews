package com.example.daylightnews.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResult")
    val totalResults: Int
):Serializable