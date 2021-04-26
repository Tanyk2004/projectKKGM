package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fadein , R.anim.righttoleft)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var txt = findViewById<TextView>(R.id.txt_go_to_sign_up)
       txt.setOnClickListener {
           startActivity(Intent(this, sign_up::class.java))

       }

}}