package com.example.daylightnews.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylightnews.R
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.adapter.SourceAdapter
import com.example.daylightnews.base.BaseFragment
import com.example.daylightnews.utils.Constants
import com.example.daylightnews.utils.Resource
import kotlinx.android.synthetic.main.fragment_source.*

//import kotlinx.android.synthetic.main.fragment_search_news.*

class SourceFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVM()
        setupRecycleView()

        sourceAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putSerializable("source",it)
            }
            findNavController().navigate(R.id.action_sourceFragment2_to_dashboardFragment,bundle)
        }

    }

    fun initVM(){
        sViewModel.sourceNews.observe(viewLifecycleOwner, Observer{ response ->
            when(response){
                is Resource.Success -> {
                    Log.d("success",response.data?.status.toString())
                    hideProgressBar()
                    response.data?.let{ sourceResponse ->
                        sourceAdapter.differ.submitList(sourceResponse.sources)
                    }
                }

                is Resource.Error -> {
                    Log.d("loading",response.data?.status.toString())
                    hideProgressBar()
                    response.message?.let{ message ->
                        Log.e(TAG, "An error occured : $message")
                    }
                }
                is Resource.Loading -> {
                    Log.d("loading",response.data?.status.toString())
                    showProgressBar()
                }
            }
        })
    }

    fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
//        isLoading = false
    }

    fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
//        isLoading = true
    }
    fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        sourceAdapter = SourceAdapter()
        rcSource.apply {
            adapter = sourceAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}