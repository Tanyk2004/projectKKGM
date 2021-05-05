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
        var txt1 = findViewById<TextView>(R.id.textView4)
        val txtlength = txt1.text.toString().length
        val unique_code = user.uid.toString().substring(0 , 4)
        txt1.text = user.uid.toString().substring(0 , 4)
        var setting_button =findViewById<Button>(R.id.btn_settings)
        setting_button.setOnClickListener {
            startActivity(Intent(this, settings::class.java))
            finish()
        }
        var spinner = findViewById<Spinner>(R.id.spinner2)
        var a = arrayOf("item 1" , "item2")
        var araryAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            a)

        spinner.adapter = araryAdapter

    }
}