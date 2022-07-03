package com.example.daylightnews.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.daylightnews.R
import com.example.daylightnews.base.BaseFragment
import com.example.daylightnews.ui.NewsActivity
import com.example.daylightnews.viewmodel.NewsVM
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : BaseFragment() {


    val args : ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        Log.d("articleFragment -> id","${article.id}")
        Log.d("articleFragment -> author","${article.author}")
        Log.d("articleFragment -> content","${article.content}")
        Log.d("articleFragment -> description","${article.description}")
        Log.d("articleFragment -> publish","${article.publishedAt}")
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url!!)
        }
    }


}