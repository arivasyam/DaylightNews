package com.example.daylightnews.repository

import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.service.RetrofitInstance

class SourceRepository() {
    suspend fun getSourceNews(category:String,pageNumber:Int ) =
        RetrofitInstance.api.getSourceNews(category,pageNumber)
}