package com.shuklansh.newsapp.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shuklansh.newsapp.Communicator

import com.shuklansh.newsapp.Fragments.NewsDisplayFragment
import com.shuklansh.newsapp.MainActivity
import com.shuklansh.newsapp.Models.ArticleModel
import com.shuklansh.newsapp.Models.imgPlusLink
import com.shuklansh.newsapp.R
import com.squareup.picasso.Picasso
//val context : Context
class categoryRecyclerAdapter(val context: Context,val articlesList : ArrayList<imgPlusLink>): RecyclerView.Adapter<categoryRecyclerAdapter.categoryRecyclerViewHolder>() {

    lateinit var categoryname : String
    lateinit var communicator: Communicator


    class categoryRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoryText : TextView = itemView.findViewById(R.id.idTextCategory)
        val categoryImgView : ImageView = itemView.findViewById(R.id.idImgCategory)
        val parentLayout : RelativeLayout = itemView.findViewById(R.id.parentCategoryLayoutRelative)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_list_item,parent,false)
        return categoryRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: categoryRecyclerViewHolder, position: Int) {
        val text = articlesList[position]
        categoryname = text.categoryName
        holder.categoryText.text = text.categoryName
        Picasso.get().load(text.categoryImgLink).into(holder.categoryImgView)

        communicator = context as Communicator

        holder.parentLayout.setOnClickListener {
            categoryname = text.categoryName
//            val intent = Intent(context, MainActivity::class.java)
//            intent.putExtra("categoryname", categoryname)
//            context?.startActivity(intent)

//            val bundle = Bundle()
//            bundle.putString("categoryFromCatRV",categoryname)
//            val contentFrag = NewsDisplayFragment()
//            contentFrag.arguments = bundle
//            println(categoryname)

            communicator.passData(categoryname)


        }
    }
    override fun getItemCount(): Int {
        return articlesList.size
    }

}