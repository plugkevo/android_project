package com.example.androidproject.ui.services

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.R
import com.squareup.picasso.Picasso

class view_goods : AppCompatActivity() {


    private lateinit var displaygoodname: TextView
    private lateinit var displaycharge: TextView
    private lateinit var displayimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_goods)

        initView()
        setValuesToViews()
    }

    private fun initView() {
        //initializing
        displaygoodname = findViewById(R.id.displaygoodname)
        displaycharge = findViewById(R.id.displaycharge)
        displayimage = findViewById(R.id.displayimage)
    }


        private fun setValuesToViews() {
            displaygoodname.text = intent.getStringExtra("goodsname")
            displaycharge.text = intent.getStringExtra("gdscharge")

            val imageUrl = intent.getStringExtra("goodimage") // Retrieve the image URL from the intent

            // Load the image using Picasso
            Picasso.get()
                .load(imageUrl)
                .into(displayimage)






        }




        }

