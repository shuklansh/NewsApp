package com.shuklansh.newsapp.Models

import com.google.gson.JsonObject
import org.json.JSONObject

data class ArticleModel (

    //val author : String,
    val title : String,
    val description : String,
    val content : String,
    val url : String,
    val image : String,
    val publishedAt : String,
    val source : JSONObject

)