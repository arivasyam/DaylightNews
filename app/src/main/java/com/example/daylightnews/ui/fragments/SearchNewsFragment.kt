package com.example.daylightnews.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylightnews.R
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.base.BaseFragment
import com.example.daylightnews.ui.NewsActivity
import com.example.daylightnews.utils.Resource
import com.example.daylightnews.viewmodel.NewsVM
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchNewsFragment : BaseFragment() {
    val TAG = "Search News Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVm()
        setupRecycleView()


        var job : Job? = null
        et_search_news.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let{
                    if(it.toString().isNotEmpty()){
                        viewModel.searchNews(it.toString())
                    }
                }
            }
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment,bundle)
        }

    }

    private fun initVm(){
//       val newsRepository = NewsRepository(NewsDatabase(view?.context!!))
//       val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
//       viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsVM::class.java)

        viewModel.searchNews.observe(viewLifecycleOwner, Observer{ response ->
            when(response){
                is Resource.Success -> {
                    Log.d("success",response.data?.totalResults.toString())
                    hideProgressBar()
                    response.data?.let{ newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }

                is Resource.Error -> {
                    Log.d("loading",response.data?.totalResults.toString())
                    hideProgressBar()
                    response.message?.let{ message ->
                        Log.e(TAG, "An error occured : $message")
                    }
                }
                is Resource.Loading -> {
                    Log.d("loading",response.data?.totalResults.toString())
                    showProgressBar()
                }
            }
        })
    }
    fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        rcSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}