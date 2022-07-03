package com.example.daylightnews.repository

import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.service.RetrofitInstance

class NewsRepository(val db : NewsDatabase) {

    suspend fun getBreakingNews(countryCode : String,pageNumber:Int ) =
        RetrofitInstance.api.getHeadlineNews(countryCode,pageNumber)

    suspend fun searchNews(searchText :String,pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchText,pageNumber)

    suspend fun getnews(category: String,pageNumber: Int) =
        RetrofitInstance.api.searchNews(category,pageNumber)
}