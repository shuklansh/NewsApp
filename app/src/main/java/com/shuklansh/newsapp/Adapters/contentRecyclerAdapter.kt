package com.shuklansh.newsapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shuklansh.newsapp.Models.ArticleModel
import com.shuklansh.newsapp.R
import com.squareup.picasso.Picasso

class contentRecyclerAdapter(val context: Context , val ArticleDetails : ArrayList<ArticleModel>) : RecyclerView.Adapter<contentRecyclerAdapter.contentRecyclerViewHolder>(){
    class contentRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleText : TextView = itemView.findViewById(R.id.textTitle)
        val authorText : TextView = itemView.findViewById(R.id.textSource)
        val imgViewArticle : ImageView = itemView.findViewById(R.id.imgHeadline)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contentRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.headline_list_item,parent,false)
        return contentRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: contentRecyclerViewHolder, position: Int) {
        val data = ArticleDetails[position]
        holder.authorText.text = data.source.getString("name")
        holder.titleText.text = data.title
        Picasso.get().load(data.image).into(holder.imgViewArticle)

    }

    override fun getItemCount(): Int {
       return ArticleDetails.size
    }
}