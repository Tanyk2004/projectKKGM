package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class username : AppCompatActivity() {
    var data = ArrayList<String>()
    private lateinit var auth:FirebaseAuth
    var user_uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
        auth = Firebase.auth
        val user : FirebaseUser? = auth.currentUser
        user_uid = user?.uid.toString()
        val db = Firebase.firestore




        var profile_button = findViewById<Button>(R.id.btn_profile)
        profile_button.setOnClickListener {

            var username_profile= findViewById<TextView>(R.id.txt_username).text.toString()
            var unique_id  = findViewById<TextView>(R.id.txt_unique_id).text.toString()

            var can_complete = check_uniqueness(unique_id)
            if ( can_complete){
            var userInfo = hashMapOf(

                "username" to username_profile,
                "unique_id" to unique_id,
                "uid" to user_uid
            )

        db.collection("users").document(user_uid).set(userInfo)
                .addOnSuccessListener {  Toast.makeText(baseContext , "user info added successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, dashboard :: class.java))
                }
            }
        }

    }
    var try1 = false
    fun check_uniqueness(Id : String) : Boolean
    {
        val db = Firebase.firestore


        var thiefs = db.collection("users").whereEqualTo("unique_id" , Id.trim()).get()
            .addOnSuccessListener { document ->
                if(document != null){
               for ( i in document){
                   data.add(i.get("unique_id").toString())

               }
                    if ( data == ArrayList<String>()){
                        try1 = true
                        data = ArrayList<String>()
                    }
                    else {
                        var unique_id  = findViewById<TextView>(R.id.txt_unique_id)
                        unique_id.error = "Sorry this code has already been taken just like your crush"
                        unique_id.requestFocus()
                        try1  = false
                        data = ArrayList<String>()
                        }
                }
                else{
                    try1 = false
                    data = ArrayList<String>()
                }

            }
        return try1
    }
}