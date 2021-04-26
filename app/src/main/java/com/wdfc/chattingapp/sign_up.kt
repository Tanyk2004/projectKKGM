package com.wdfc.chattingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class sign_up : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        overridePendingTransition(R.anim.righttoleft,R.anim.fadeout)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = Firebase.auth
        var animation = AnimationUtils.loadAnimation(this, R.anim.zoomin)
        var logo_view = findViewById<ImageView>(R.id.babble_logo2)
        logo_view.startAnimation(animation)
        var sign_up_button = findViewById<Button>(R.id.btn_sign_up)
        sign_up_button.startAnimation(animation)


        //************************ SIGN UP CREDENTIALS ************************************
        var sign_up_email = findViewById<TextView>(R.id.txt_email_sign_up)
        var  sign_up_password = findViewById<TextView>(R.id.txt_sign_up_password)
        var sign_up_password_confirm = findViewById<TextView>(R.id.txt_confirm_password)
        //************************ SIGN UP CREDENTIALS ************************************


        sign_up_button.setOnClickListener {
            var can_login = checkCreds()
            if (can_login){
            auth.createUserWithEmailAndPassword(sign_up_email.text.toString(), sign_up_password.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        startActivity(Intent(this, dashboard :: class.java))
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext , "Get some internet you little shit", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
    private fun checkCreds():Boolean{
        var sign_up_email = findViewById<TextView>(R.id.txt_email_sign_up)
        var  sign_up_password = findViewById<TextView>(R.id.txt_sign_up_password)
        var sign_up_password_confirm = findViewById<TextView>(R.id.txt_confirm_password)

        if(sign_up_email.text.toString().isEmpty()){
            sign_up_email.error = "Please enter an email"
            sign_up_email.requestFocus()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(sign_up_email.text.toString()).matches()){
            sign_up_email.error = "Please enter valid email"
            sign_up_email.requestFocus()
            return false
        }
        if(sign_up_password.text.toString().isEmpty()){
            sign_up_password.error = "Please enter a password"
            sign_up_password.requestFocus()
            return false
        }
        if(sign_up_password_confirm.text.toString().isEmpty()){
            sign_up_password_confirm.error = "Please enter a password"
            sign_up_password_confirm.requestFocus()
            return false
        }
        if(sign_up_password_confirm.text.toString().length < 8){
            sign_up_password.error = "a password should atleast be 8 characters long"
            sign_up_password.requestFocus()
            return false
        }
        if ( !sign_up_password.text.toString().equals(sign_up_password_confirm.text.toString())){
            sign_up_password.error = "The two passwords do not match"
            sign_up_password.requestFocus()
            sign_up_password_confirm.requestFocus()
            return false
        }
        else{
            return true
        }
    }
}