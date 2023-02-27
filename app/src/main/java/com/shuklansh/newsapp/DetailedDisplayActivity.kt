package com.shuklansh.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class DetailedDisplayActivity : AppCompatActivity() {

    lateinit var imgDetail : ImageView
    lateinit var titleDetail : TextView
    lateinit var publishedDetail : TextView
    lateinit var descriptionDetail : TextView

    lateinit var titleText : String
    lateinit var publishedText : String
    lateinit var descriptionText : String
    lateinit var authorText : String
    lateinit var imglink : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_display)

        imgDetail = findViewById(R.id.imgHeadlineDetail)
        titleDetail = findViewById(R.id.textTitleDetail)
        publishedDetail = findViewById(R.id.textSourceDetail)
        descriptionDetail = findViewById(R.id.descriptionDetail)

        if(intent != null) {
            titleText = intent.getStringExtra("title").toString()
            authorText = intent.getStringExtra("author").toString()
            imglink = intent.getStringExtra("linkoftheImage").toString()
            publishedText = intent.getStringExtra("publishedAt").toString()
            descriptionText = intent.getStringExtra("description").toString()

        }
        println(imglink)
        Picasso.get().load(imglink).into(imgDetail)
        titleDetail.text = titleText
        publishedDetail.text = publishedText
        descriptionDetail.text = descriptionText

    }
}