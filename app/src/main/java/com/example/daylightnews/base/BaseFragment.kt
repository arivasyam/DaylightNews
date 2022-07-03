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
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.ui.NewsActivity
import com.example.daylightnews.ui.fragments.SearchNewsFragment
import com.example.daylightnews.viewmodel.NewsVM
import com.example.daylightnews.viewmodel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_base.*

//import kotlinx.android.synthetic.main.fragment_search_news.*


open class BaseFragment : Fragment() {
    lateinit var newsAdapter:NewsAdapter
    lateinit var viewModel:NewsVM

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
    }

    fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }




}