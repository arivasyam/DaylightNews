package com.example.daylightnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daylightnews.model.NewsResponse
import com.example.daylightnews.model.SourceResponse
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.repository.SourceRepository
import com.example.daylightnews.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SourceVM(val sourceRepository : SourceRepository) : ViewModel() {
    var sourceNews : MutableLiveData<Resource<SourceResponse>> = MutableLiveData()
    var sourcePage = 1
    var sourceNewsResponse: SourceResponse? = null
//    init {
//        getSourceNews("general")
//    }

    fun getSourceNews(category:String) = viewModelScope.launch {
            sourceNews.postValue(Resource.Loading())
            val response = sourceRepository.getSourceNews(category,sourcePage)
            sourceNews.postValue(handleSourceNewsResponse(response))
    }

    private fun handleSourceNewsResponse(response: Response<SourceResponse>): Resource<SourceResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                sourcePage++
                if(sourceNewsResponse == null){
                    sourceNewsResponse = resultResponse
                }else{
                    val oldArticles = sourceNewsResponse?.sources
                    val newArticles = resultResponse.sources
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(sourceNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
