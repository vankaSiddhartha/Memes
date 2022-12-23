package com.example.memesman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        load()
    }

    private fun load() {
        val queue = singletonvolley.getInstance(this.applicationContext).requestQueue
        val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
            { response ->
                val url = response.getString("url")
                Glide.with(this).load(url).into(findViewById(R.id.Source))

            },
            {

            })

// Add the request to the RequestQueue.
        singletonvolley.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun next(view: View) {
        load()
    }

    fun share(view: View) {

            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT,"SHare Bro!!")
            val chooser = Intent.createChooser(intent,"Share this meme useing")
            startActivity(chooser)
        }
    }


