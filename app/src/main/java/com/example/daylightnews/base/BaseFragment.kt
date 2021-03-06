package com.example.daylightnews.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylightnews.R
import com.example.daylightnews.adapter.CategoryAdapter
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.adapter.SourceAdapter
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.repository.SourceRepository
import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.ui.NewsActivity
import com.example.daylightnews.ui.fragments.CategoryFragment
import com.example.daylightnews.ui.fragments.SearchNewsFragment
import com.example.daylightnews.viewmodel.NewsVM
import com.example.daylightnews.viewmodel.NewsViewModelProviderFactory
import com.example.daylightnews.viewmodel.SourceVM
import com.example.daylightnews.viewmodel.SourceViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_base.*

//import kotlinx.android.synthetic.main.fragment_search_news.*


open class BaseFragment : Fragment() {
    lateinit var newsAdapter:NewsAdapter
    lateinit var sourceAdapter:SourceAdapter
    lateinit var categoryAdapter: CategoryAdapter
    lateinit var viewModel:NewsVM
    lateinit var sViewModel : SourceVM



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        val newsRepository = NewsRepository(NewsDatabase(activity?.baseContext!!))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsVM::class.java)


        val sourceRepository = SourceRepository()
        val sViewModelProviderFactory = SourceViewModelProviderFactory(sourceRepository)
        sViewModel = ViewModelProvider(this, sViewModelProviderFactory).get(SourceVM::class.java)
    }

//    fun hideProgressBar(){
//        paginationProgressBar.visibility = View.INVISIBLE
//        isLoading = false
//    }
//
//    fun showProgressBar(){
//        paginationProgressBar.visibility = View.VISIBLE
//        isLoading = true
//    }




}