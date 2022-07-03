package com.example.daylightnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.repository.SourceRepository

class SourceViewModelProviderFactory (val sourceRepository: SourceRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SourceVM(sourceRepository) as T
    }
}