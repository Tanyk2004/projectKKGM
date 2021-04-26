package com.wdfc.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.fadeout)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var logo_animation = AnimationUtils.loadAnimation(this, R.anim.zoomin)
        var logo_view = findViewById<ImageView>(R.id.babble_logo2)
        logo_view.startAnimation(logo_animation)
        var animation = AnimationUtils.loadAnimation(this, R.anim.zoomin)
        var sign_up_button = findViewById<Button>(R.id.btn_sign_up)
        sign_up_button.startAnimation(animation)
        sign_up_button.setOnClickListener {

        }
    }
}