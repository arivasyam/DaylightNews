package com.example.daylightnews.model

data class SourceResponse(
    val sources: MutableList<SourceX>,
    val status: String
)