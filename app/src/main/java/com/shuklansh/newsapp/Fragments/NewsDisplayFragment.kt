package com.shuklansh.newsapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shuklansh.newsapp.Adapters.categoryRecyclerAdapter
import com.shuklansh.newsapp.Adapters.contentRecyclerAdapter
import com.shuklansh.newsapp.Models.ArticleModel
import com.shuklansh.newsapp.Models.sourceDataModel
import com.shuklansh.newsapp.R
import com.shuklansh.newsapp.databinding.ActivityMainBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class NewsDisplayFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    // Content recycler vars
    lateinit var recyclerContent: RecyclerView
    lateinit var contentLayoutManager: RecyclerView.LayoutManager
    lateinit var contentRecyclerAdapter: contentRecyclerAdapter

    //lateinit var binding : ActivityMainBinding

    var ArticleDetails = arrayListOf<ArticleModel>()
    lateinit var categoryName : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //val args = this.arguments
        //categoryName= args?.get("categoryFromCatRV")
        //categoryName = args?.getString("categoryFromCatRV").toString()

        categoryName = arguments?.getString("category").toString()

        //println("in frag : ${categoryName}")
        //categoryName = "general"
        val view = inflater.inflate(R.layout.fragment_news_display, container, false)
        recyclerContent = view.findViewById(R.id.recyclerContent)
        val apikey = "2ba40b1f3eee81067a9100fcb580bb4b";

        val url = "https://gnews.io/api/v4/top-headlines?category=${categoryName}&lang=en&country=in&max=10&apikey=${apikey}";

        val queue = Volley.newRequestQueue(activity as Context)


        //this is the url of server from where we will get the response. WE ALSO NEED A TOKEN ALONG WITH URL
        //anonymous object syntax. header will be sent with url


        // check connectivity to internet and if true, proceed to get response and map data
        val jsonObjectRequest = object :
            JsonObjectRequest( //JOR method arguments: ReqMethod , url , jsonObject, ResponseListener, ErrorListener
                Request.Method.GET, url, null, Response.Listener {
                    val data = it.getJSONArray("articles")
                    for(i in 0 until data.length()){
                        val JsonObject = data.getJSONObject(i)
                        val article = ArticleModel(
                            JsonObject.getString("title"),
                            JsonObject.getString("description"),
                            JsonObject.getString("content"),
                            JsonObject.getString("url"),
                            JsonObject.getString("image"),
                            JsonObject.getString("publishedAt"),
                            JsonObject.getJSONObject("source")
                        )
                        ArticleDetails.add(article)
                    }

                    contentLayoutManager = LinearLayoutManager(activity as Context, LinearLayoutManager.VERTICAL,false)
                    contentRecyclerAdapter = contentRecyclerAdapter(activity as Context,ArticleDetails)

                    recyclerContent.adapter = contentRecyclerAdapter
                    recyclerContent.layoutManager = contentLayoutManager


                }, Response.ErrorListener { // so app does not crash due to no activity
                    //  VOLLEY ERRORS ARE HANDLED IN THE RESPONSE.ERRORLISTENER BLOCK. SO WE PUT TOAST HERE SAYING VOLLEY ERROR OCCURED
                    println("volley error occured $it")
                }) { //inside JsonObjectRequest(args) : we also send headers to validate unique request
            //GETHEADERS METHOD TO SEND HEADER TO API
            override fun getHeaders(): MutableMap<String, String> {
                //headers are used to ensure each request is UNIQUE
                //hashmap is another name for dictionary. so data is being received in string:string
                val headers = HashMap<String, String>() //hashmap is derived from a mutable map
                //headers["Content-type"] ="application/json" //key: content-type, value: application/json
                //headers[" "] = " " //key: token, value: unique key given
                return headers //this is sent to API.
            }

        }

        queue.add(jsonObjectRequest)



        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsDisplayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}