package com.sherina.mammalsclassification

import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class MammalsDetails : AppCompatActivity() {
    private lateinit var ivMammalsPhotoDetail : ImageView
    private lateinit var txtMammalsNameDetail : TextView
    private lateinit var txtMammalsInfoDetail : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mammals_details)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        ivMammalsPhotoDetail = findViewById(R.id.tvMammalsPhotoDetail)
        txtMammalsNameDetail = findViewById(R.id.mammalsNameDetail)
        txtMammalsInfoDetail = findViewById(R.id.mammalsInfoDetail)


        val gambar = intent.getIntExtra("photo", 0)

        Glide.with(this)
            .load(gambar)
            .apply(RequestOptions(). override(270,270))
            .into(ivMammalsPhotoDetail)

        txtMammalsNameDetail.text = intent.getStringExtra("name")
        txtMammalsInfoDetail.text = intent.getStringExtra("information")

        actionBar?.title = intent.getStringExtra("name")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}