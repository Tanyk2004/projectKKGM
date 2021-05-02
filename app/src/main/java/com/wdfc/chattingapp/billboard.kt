package com.wdfc.chattingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wdfc.chattingapp.fragments.add_profiles
import com.wdfc.chattingapp.fragments.view_profiles

class billboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billboard)

        var view = view_profiles()
        var add = add_profiles()
        makeCurrentFragment(view)

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

