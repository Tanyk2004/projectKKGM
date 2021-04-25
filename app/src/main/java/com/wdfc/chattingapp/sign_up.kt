package com.wdfc.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.fadeout)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var animation = AnimationUtils.loadAnimation(this, R.anim.zoomin)
        var sign_up_button = findViewById<Button>(R.id.btn_sign_up)
        sign_up_button.startAnimation(animation)
    }
}