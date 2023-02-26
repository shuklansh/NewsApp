package com.shuklansh.newsapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.shuklansh.newsapp.Models.ArticleModel
import com.shuklansh.newsapp.Models.imgPlusLink
import com.shuklansh.newsapp.R
import com.squareup.picasso.Picasso

class categoryRecyclerAdapter(val context : Context,val articlesList : ArrayList<imgPlusLink>) : RecyclerView.Adapter<categoryRecyclerAdapter.categoryRecyclerViewHolder>() {

    class categoryRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryText : TextView = itemView.findViewById(R.id.idTextCategory)
        val categoryImgView : ImageView = itemView.findViewById(R.id.idImgCategory)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item,parent,false)
        return categoryRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: categoryRecyclerViewHolder, position: Int) {
        val text = articlesList[position]
        holder.categoryText.text = text.categoryName
        Picasso.get().load(text.categoryImgLink).into(holder.categoryImgView)
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

}