package com.example.daylightnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daylightnews.R
import com.example.daylightnews.model.SourceX
import kotlinx.android.synthetic.main.category_holder.view.*

class CategoryAdapter(val data:List<String>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_holder,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.itemView.apply {
            cCategory.text = data.get(position).toString()
            setOnClickListener {
                onItemClickListener?.let { it(data.get(position)) }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}