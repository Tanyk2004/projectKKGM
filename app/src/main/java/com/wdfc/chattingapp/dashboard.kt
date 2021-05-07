package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class dashboard : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.lefttoright,R.anim.right_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        auth = Firebase.auth
        val user =  auth.currentUser



        var add_chats = findViewById<Button>(R.id.btn_add_chats)
        var setting_button =findViewById<Button>(R.id.btn_settings)
        setting_button.setOnClickListener {
            startActivity(Intent(this, settings::class.java))
            finish()
        }
        add_chats.setOnClickListener {
            startActivity(Intent(this, add_chats :: class.java))
            finish()

        }






    }
}