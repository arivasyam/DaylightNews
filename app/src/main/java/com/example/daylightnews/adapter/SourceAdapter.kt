package com.example.daylightnews.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daylightnews.R
import com.example.daylightnews.model.Article
import com.example.daylightnews.model.SourceX
import kotlinx.android.synthetic.main.article_holder.view.*
import kotlinx.android.synthetic.main.source_view_holder.view.*

class SourceAdapter: RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {
    inner class SourceViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    private val differCallback =  object : DiffUtil.ItemCallback<SourceX>() {
        override fun areItemsTheSame(oldItem: SourceX, newItem: SourceX): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: SourceX, newItem: SourceX): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer( this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.source_view_holder,parent,false))
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val source = differ.currentList[position]
        Log.d("sourceadapter","$source.category")
        Log.d("sourceadapter","$source.country")
        Log.d("sourceadapter","$source.description")
        Log.d("sourceadapter","$source.language")
        Log.d("sourceadapter","$source.name")
        Log.d("sourceadapter","$source.url")
        holder.itemView.apply {
            sCategory.text = "Category : ${source.category }"
            sCountry.text = "Country : ${source.country }"
            sDescription.text = "Description : ${source.description }"
            sName.text = source.name
            setOnClickListener {
                onItemClickListener?.let { it(source) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SourceX) -> Unit)? = null

    fun setOnItemClickListener(listener: (SourceX) -> Unit) {
        onItemClickListener = listener
    }
}