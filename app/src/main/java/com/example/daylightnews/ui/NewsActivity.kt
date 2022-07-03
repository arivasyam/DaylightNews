package com.example.daylightnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.daylightnews.R
import com.example.daylightnews.base.BaseActivity
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.viewmodel.NewsVM
import com.example.daylightnews.viewmodel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : BaseActivity() {
//    lateinit var viewModel : NewsVM



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val newsRepository = NewsRepository(NewsDatabase(this))
//        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
//        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsVM::class.java)
        setContentView(R.layout.activity_news)

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}