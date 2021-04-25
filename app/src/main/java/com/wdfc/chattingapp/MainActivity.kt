package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fadein , R.anim.fadeout)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var txt = findViewById<TextView>(R.id.UwU)
       txt.setOnClickListener {
           startActivity(Intent(this, sign_up::class.java))
           finish()
       }

}}