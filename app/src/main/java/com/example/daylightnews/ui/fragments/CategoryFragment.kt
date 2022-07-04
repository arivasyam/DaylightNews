package com.example.daylightnews.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylightnews.R
import com.example.daylightnews.adapter.CategoryAdapter
import com.example.daylightnews.adapter.NewsAdapter
import com.example.daylightnews.adapter.SourceAdapter
import com.example.daylightnews.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_source.*

class CategoryFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        initCategory()

        categoryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply{
                putString("category",it)
            }
            findNavController().navigate(R.id.action_categoryFragment_to_sourceFragment2,bundle)
        }

    }

    fun initCategory(){
        val listCategory = listOf<String>("Health","Business","Science","Entertainment","Sports","General","Technology")
        categoryAdapter = CategoryAdapter(listCategory)
        rcCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        sourceAdapter = SourceAdapter()

    }
}