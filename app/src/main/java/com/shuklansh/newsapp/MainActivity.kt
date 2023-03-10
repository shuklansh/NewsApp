package com.shuklansh.newsapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.shuklansh.newsapp.Adapters.categoryRecyclerAdapter
import com.shuklansh.newsapp.Fragments.CategoryFragment
import com.shuklansh.newsapp.Fragments.NewsDisplayFragment
import com.shuklansh.newsapp.Models.imgPlusLink
import org.json.JSONException

class MainActivity : AppCompatActivity() , Communicator {

    // Category recycler vars
//    lateinit var recyclerCategory: RecyclerView
//    lateinit var categoryLayoutManager: RecyclerView.LayoutManager
//    lateinit var categoryRecyclerAdapter: categoryRecyclerAdapter


//    // Content recycler vars
//    lateinit var recyclerContent: RecyclerView
//    lateinit var contentLayoutManager: RecyclerView.LayoutManager
//    lateinit var contentRecyclerAdapter: categoryRecyclerAdapter

    lateinit var categoryname: String
    lateinit var url: String
    lateinit var frameLayoutReplace : FrameLayout
    lateinit var frameLayoutReplaceCat : FrameLayout

    val categoryList = arrayListOf<imgPlusLink>(
        imgPlusLink("general","https://images.unsplash.com/photo-1518057111178-44a106bad636?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=388&q=80"),
        imgPlusLink("world","https://images.unsplash.com/photo-1531266752426-aad472b7bbf4?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"),
        imgPlusLink("nation","https://images.unsplash.com/photo-1532375810709-75b1da00537c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=876&q=80"),
        imgPlusLink("business","https://images.unsplash.com/photo-1460925895917-afdab827c52f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=815&q=80"),
        imgPlusLink("technology","https://images.unsplash.com/photo-1519389950473-47ba0277781c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"),
        imgPlusLink("entertainment","https://images.unsplash.com/photo-1563341591-a4ef278eb34b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"),
        imgPlusLink("sports","https://images.unsplash.com/photo-1579952363873-27f3bade9f55?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=435&q=80"),
        imgPlusLink("science","https://images.unsplash.com/photo-1582719471384-894fbb16e074?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"),
        imgPlusLink("health","https://images.unsplash.com/photo-1594882645126-14020914d58d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=885&q=80"))

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayoutReplace = findViewById(R.id.frameReplace)
        frameLayoutReplaceCat = findViewById(R.id.frameReplaceCat)

        //recyclerCategory = findViewById(R.id.recyclerCategory)
        //recyclerContent = findViewById(R.id.recyclerContent)




        //categoryname = "general"
//        val apikey : String = "2ba40b1f3eee81067a9100fcb580bb4b"
//        val url = "https://gnews.io/api/v4/top-headlines?category=general&lang=en&country=in&max=10&apikey=${apikey}"
//        val queue = Volley.newRequestQueue(applicationContext)
//
//        //this is the url of server from where we will get the response. WE ALSO NEED A TOKEN ALONG WITH URL
//        //anonymous object syntax. header will be sent with url
//
//
//        // check connectivity to internet and if true, proceed to get response and map data
//        val jsonObjectRequest = object :
//            JsonObjectRequest( //JOR method arguments: ReqMethod , url , jsonObject, ResponseListener, ErrorListener
//                Request.Method.GET, url, null, Response.Listener {
//                    println("Response is : $it")
//                }, Response.ErrorListener { // so app does not crash due to no activity
//                    //  VOLLEY ERRORS ARE HANDLED IN THE RESPONSE.ERRORLISTENER BLOCK. SO WE PUT TOAST HERE SAYING VOLLEY ERROR OCCURED
//                    println("volley error occured $it")
//                }) { //inside JsonObjectRequest(args) : we also send headers to validate unique request
//            //GETHEADERS METHOD TO SEND HEADER TO API
//            override fun getHeaders(): MutableMap<String, String> {
//                //headers are used to ensure each request is UNIQUE
//                //hashmap is another name for dictionary. so data is being received in string:string
//                val headers = HashMap<String, String>() //hashmap is derived from a mutable map
//                //headers["Content-type"] ="application/json" //key: content-type, value: application/json
//                //headers[" "] = " " //key: token, value: unique key given
//                return headers //this is sent to API.
//            }
//        }
//
//        queue.add(jsonObjectRequest)

//        if(intent!=null){
//            categoryname = intent.getStringExtra("categoryname").toString()
//        }

//        categoryLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
//        categoryRecyclerAdapter = categoryRecyclerAdapter(this@MainActivity,categoryList)
//        recyclerCategory.adapter = categoryRecyclerAdapter
//        recyclerCategory.layoutManager = categoryLayoutManager



        supportFragmentManager.beginTransaction()
            .replace(R.id.frameReplace,NewsDisplayFragment())
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameReplaceCat,CategoryFragment())
            .commit()


    }

    override fun onBackPressed() {
        /*
        This function is for directing user to dashboard fragment from any other fragment, when back btn is pressed.
        If dashboard frag is open already, then back btn will behave in its default manner i.e. exit app.
         */
        val fragmentCurrent = supportFragmentManager.findFragmentById(R.id.frameReplace) //fragmentCurrent variable will hold the fragment currently in the frameLayout
        when(fragmentCurrent){ //fragmentcurrent is being used as arguement for when function (switch case)
            !is NewsDisplayFragment -> openNewsDisplayFragment() //when current fragment is not dashboard fragment -> open dashboard fragment (function to open dashboard frag is called)
            else->super.onBackPressed() //if the fragment currently in the framelayout is dashboard frag, then make the back btn behave in default manner i.e. exit app
        }
    }

    fun openNewsDisplayFragment(){
        val dashbordFrag= NewsDisplayFragment() //NewsDisplayFragment() is the fragment file for the dashboard, being stored in the variable dashbordFrag.
        val transaction= supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameReplace,dashbordFrag)
        transaction.commit()
    }

    override fun passData(categoryCOMM: String) {
        val bundle = Bundle()
        bundle.putString("category",categoryCOMM)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentContent = NewsDisplayFragment()
        fragmentContent.arguments = bundle
        transaction.replace(R.id.frameReplace , fragmentContent)
        transaction.commit()
    }


}