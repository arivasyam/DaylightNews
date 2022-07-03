package com.example.daylightnews.repository

import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.service.RetrofitInstance

class SourceRepository() {
    suspend fun getSourceNews(pageNumber:Int ) =
        RetrofitInstance.api.getSourceNews(pageNumber)
}