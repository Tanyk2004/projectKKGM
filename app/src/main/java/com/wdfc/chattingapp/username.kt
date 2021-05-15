package com.wdfc.chattingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class username : AppCompatActivity() {
    var data = ArrayList<String>()
    private lateinit var auth:FirebaseAuth
    var user_uid = ""
    var try1 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.righttoleft,R.anim.left_out)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)
        auth = Firebase.auth
        val user : FirebaseUser? = auth.currentUser
        user_uid = user?.uid.toString()
        val db = Firebase.firestore
        val imgbtn1 = findViewById<ImageButton>(R.id.imageButton)
        val imgbtn2 = findViewById<ImageButton>(R.id.imageButton2)
        val imgbtn3 = findViewById<ImageButton>(R.id.imageButton3)
        val imgbtn4 = findViewById<ImageButton>(R.id.imageButton4)
        val imgbtn5 = findViewById<ImageButton>(R.id.imageButton5)
        imgbtn1.setOnClickListener{

            imgbtn2.isEnabled=!imgbtn2.isEnabled
            imgbtn3.isEnabled=!imgbtn3.isEnabled
            imgbtn4.isEnabled=!imgbtn4.isEnabled
            imgbtn5.isEnabled=!imgbtn5.isEnabled

        }
        imgbtn2.setOnClickListener{

            imgbtn1.isEnabled=!imgbtn1.isEnabled
            imgbtn3.isEnabled=!imgbtn3.isEnabled
            imgbtn4.isEnabled=!imgbtn4.isEnabled
            imgbtn5.isEnabled=!imgbtn5.isEnabled

        }
        imgbtn3.setOnClickListener{

            imgbtn2.isEnabled=!imgbtn2.isEnabled
            imgbtn1.isEnabled=!imgbtn1.isEnabled
            imgbtn4.isEnabled=!imgbtn4.isEnabled
            imgbtn5.isEnabled=!imgbtn5.isEnabled

        }
        imgbtn4.setOnClickListener{

            imgbtn2.isEnabled=!imgbtn2.isEnabled
            imgbtn3.isEnabled=!imgbtn3.isEnabled
            imgbtn1.isEnabled=!imgbtn1.isEnabled
            imgbtn5.isEnabled=!imgbtn5.isEnabled

        }
        imgbtn5.setOnClickListener{
            imgbtn2.isEnabled=!imgbtn2.isEnabled
            imgbtn3.isEnabled=!imgbtn3.isEnabled
            imgbtn4.isEnabled=!imgbtn4.isEnabled
            imgbtn1.isEnabled=!imgbtn1.isEnabled

        }


        var profile_button = findViewById<Button>(R.id.btn_profile)
        profile_button.setOnClickListener {
            var username_profile= findViewById<TextView>(R.id.txt_username).text.toString()
            var unique_id  = findViewById<TextView>(R.id.txt_unique_id).text.toString()


            var thiefs = db.collection("users").whereEqualTo("unique_id" , unique_id).get()
                    .addOnSuccessListener { document ->
                            for ( i in document){
                                data.add(i.get("unique_id").toString())


                            }
                            if ( data.isEmpty()){
                                try1 = 0
                                data = ArrayList<String>()


                            }
                            else {
                                var unique_id  = findViewById<TextView>(R.id.txt_unique_id)
                                unique_id.error = "Sorry this ID has already been taken...just like your crush"
                                unique_id.requestFocus()
                                data = ArrayList<String>()

                            }
                        if (try1==0 ){
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

    }


    }
