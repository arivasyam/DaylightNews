package com.example.daylightnews.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daylightnews.R
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.base.BaseFragment
import com.example.daylightnews.model.Source
import com.example.daylightnews.repository.NewsRepository
import com.example.daylightnews.roomdb.NewsDatabase
import com.example.daylightnews.ui.NewsActivity
import com.example.daylightnews.utils.Resource
import com.example.daylightnews.viewmodel.NewsVM
import com.example.daylightnews.viewmodel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseFragment() {
    val TAG = "Breaking News Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVm()
        setupRecycleView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            Log.d("dashboardFragment -> id","${it.id}")
            Log.d("dashboardFragment -> author","${it.author}")
            Log.d("dashboardFragment -> content","${it.content}")
            Log.d("dashboardFragment -> description","${it.description}")
            Log.d("dashboardFragment -> publish","${it.publishedAt}")
            findNavController().navigate(R.id.action_dashboardFragment_to_articleFragment,bundle)
        }
    }

   private fun initVm(){
//       val newsRepository = NewsRepository(NewsDatabase(view?.context!!))
//       val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
//       viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsVM::class.java)
//
       viewModel.breakingNews.observe(viewLifecycleOwner, Observer{ response ->
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
        rcBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }



}