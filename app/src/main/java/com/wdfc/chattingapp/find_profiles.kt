package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class find_profiles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_profiles)
        val db = Firebase.firestore
        var back = findViewById<Button>(R.id.btn_back_add1)
        var id = findViewById<TextView>(R.id.txt_search_query)

        var add_chat = findViewById<Button>(R.id.btn_add_chat)
        add_chat.setOnClickListener {
            var query = id.text.toString().trim()
            db.collection("users").whereEqualTo("unique_id" , query).get()
                    .addOnSuccessListener {

                    }
        }
        back.setOnClickListener {
            startActivity(Intent(this, dashboard :: class.java))
            finish()
        }


    }
}