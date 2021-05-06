package com.wdfc.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wdfc.chattingapp.fragments.add_profiles
import com.wdfc.chattingapp.fragments.view_profiles
import org.w3c.dom.Text


class billboard : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billboard)
        val db = Firebase.firestore
        var add = add_profiles()
        var view = view_profiles()
        makeCurrentFragment(add)
        var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener { 
            when (it.itemId){
                R.id.add_profiles -> makeCurrentFragment(add)

                R.id.view_profiles -> makeCurrentFragment(view)

            }
            true
        }
    }




private fun makeCurrentFragment(fragment : Fragment) =
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper , fragment)
        commit()
    }
}

