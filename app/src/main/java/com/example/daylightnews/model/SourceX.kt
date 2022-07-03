package com.example.daylightnews.model

import androidx.room.Entity
import java.io.Serializable


@Entity(tableName = "sourcex")
data class SourceX(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
    val url: String
):Serializable