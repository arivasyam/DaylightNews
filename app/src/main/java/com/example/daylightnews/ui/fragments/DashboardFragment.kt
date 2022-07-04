package com.example.daylightnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daylightnews.R
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.base.BaseFragment
import com.example.daylightnews.utils.Constants.Companion.PAGE_SIZE
import com.example.daylightnews.utils.Resource
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.paginationProgressBar


class DashboardFragment : BaseFragment() {
    val TAG = "Breaking News Fragment"
    val args : DashboardFragmentArgs by navArgs()


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
        val source = args.source
        Toast.makeText(activity,"category headline news-> ${source.category}",Toast.LENGTH_SHORT).show()
        viewModel.getNews(source.category)



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
//                    var i = 0
                    response.data?.let{ newsResponse ->
//                        if(newsResponse.articles.get(i).source?.name == name){
//                            newsAdapter.differ.submitList(newsResponse.articles.toList())
//                            val totalPages = newsResponse.totalResults / PAGE_SIZE + 2
//                            isLastpage = viewModel.breakingNewsPage == totalPages
//                        }
//                        Log.d("loop","$i")

                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / PAGE_SIZE + 2
                        isLastpage = viewModel.breakingNewsPage == totalPages
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

    var isLoading = false
    var isLastpage = false
    var isScrolling = false


    val scrollListener = object: RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastpage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= PAGE_SIZE

            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate){
                viewModel.getBreakingNews("us")
                isScrolling = false
            }else{
                rcBreakingNews.setPadding(0,0,0,0)
            }
        }
    }

    fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        rcBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@DashboardFragment.scrollListener)
        }
    }



}