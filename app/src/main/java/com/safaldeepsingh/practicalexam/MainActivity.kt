package com.safaldeepsingh.practicalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.safaldeepsingh.practicalexam.db.CartTable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getView from Layout
        val logo: ImageView = findViewById(R.id.main_logo)

        //listeners
        logo.setOnClickListener {
            val openMenuActivity = Intent(this, MenuActivity::class.java)
            startActivity(openMenuActivity)
            finish()
        }
    }
}