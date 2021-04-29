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
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email_login:TextView
    private lateinit var password_login: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fadein , R.anim.righttoleft)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        email_login = findViewById<TextView>(R.id.txt_email_login)
        password_login = findViewById<TextView>(R.id.txt_password_login)
        var logo_animation = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        var logo_view = findViewById<ImageView>(R.id.babble_logo)
        logo_view.startAnimation(logo_animation)
        var txt = findViewById<TextView>(R.id.txt_go_to_sign_up)
        txt.setOnClickListener {
            startActivity(Intent(this, sign_up::class.java))
            finish()
        }
        var btn_login = findViewById<Button>(R.id.btn_login)
        btn_login.setOnClickListener {
            var can_login =  checkCreds()
            if ( can_login){
                auth.signInWithEmailAndPassword(email_login.text.toString(), password_login.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information

                                val user = auth.currentUser
                                updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(baseContext, "Authentication failed. \n" + task.exception.toString(),
                                        Toast.LENGTH_SHORT).show()
                                updateUI(null)
                            }
                        }
            }
        }

    }
    public override fun onStart(){
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    private fun updateUI( user : FirebaseUser?){
        if( user!= null){
            //Toast.makeText(baseContext, "Logging in", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, dashboard::class.java))
            finish()
        }
    }

    private fun checkCreds() : Boolean
    {
        var email_login = findViewById<TextView>(R.id.txt_email_login)
        var password_login = findViewById<TextView>(R.id.txt_password_login)

        if(email_login.text.toString().isEmpty()){
            email_login.error = "Please enter an email"
            email_login.requestFocus()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_login.text.toString()).matches()){
            email_login.error = "Please enter valid email"
            email_login.requestFocus()
            return false
        }
        if(password_login.text.toString().isEmpty()){
            password_login.error = "Please enter a password"
            password_login.requestFocus()
            return false
        }
        else{
            return true
        }
    }

}