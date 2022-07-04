package com.example.daylightnews.service

import com.example.daylightnews.model.NewsResponse
import com.example.daylightnews.model.SourceResponse
import com.example.daylightnews.utils.Constants.Companion.NEWS_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines")
    suspend fun getHeadlineNews(@Query("country") country: String = "us",@Query("page") pageNumber: Int = 1,
                                @Query("apiKey") apiKey:String = NEWS_API_KEY): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(@Query("q") searchText: String,
                           @Query("page") pageNumber: Int = 1,
                           @Query("apiKey") apiKey:String = NEWS_API_KEY): Response<NewsResponse>

    @GET("v2/top-headlines/sources")
    suspend fun getSourceNews(@Query("category") category: String, @Query("page") pageNumber: Int = 1,
                                @Query("apiKey") apiKey:String = NEWS_API_KEY): Response<SourceResponse>

    @GET("v2/top-headlines")
    suspend fun getNews(@Query("category") category: String,@Query("page") pageNumber: Int = 1,
                                @Query("apiKey") apiKey:String = NEWS_API_KEY): Response<NewsResponse>
}