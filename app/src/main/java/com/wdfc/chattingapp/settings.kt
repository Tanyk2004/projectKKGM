package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class settings : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = Firebase.auth
        var txtemail = findViewById<TextView>(R.id.txt_email)
        val user = auth.currentUser
        txtemail.text= user.email.toString()
        var dashboar_button = findViewById<Button>(R.id.btn_dashboard)
        dashboar_button.setOnClickListener {
            startActivity(Intent(this, dashboard::class.java))
            finish()
        }


    }
}