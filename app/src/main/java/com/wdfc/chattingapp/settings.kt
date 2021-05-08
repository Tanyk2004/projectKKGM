package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class settings : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var current_id : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = Firebase.auth
       val db = Firebase.firestore
        val user = auth.currentUser
       // Toast.makeText(baseContext, user.uid.toString() ,Toast.LENGTH_SHORT).show()
        var txt_unique = findViewById<TextView>(R.id.txt_current_unique_id)
        var disp_user = findViewById<TextView>(R.id.txt_disp_username)
        db.collection("users").whereEqualTo("uid" , user.uid.toString()).get()
            .addOnSuccessListener { document ->
                for (i in document){
                    txt_unique.text = i.get("unique_id").toString()
                    disp_user.text = i.get("username").toString()
                }
                }
        txt_unique.text = current_id
        var dashboar_button = findViewById<Button>(R.id.btn_dashboard)
        dashboar_button.setOnClickListener {
            startActivity(Intent(this, dashboard::class.java))
            overridePendingTransition(R.anim.righttoleft,R.anim.lefttoright)
            finish()
        }
        var logout_button =findViewById<Button>(R.id.btn_logout)
        logout_button.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



    }
}