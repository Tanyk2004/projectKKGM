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
    var add = add_profiles()
    var selectedState : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billboard)
        val db = Firebase.firestore
        var view = view_profiles()

        makeCurrentFragment(view)




        var add_button  = add.view?.findViewById<Button>(R.id.btn_add)
        add_button?.setOnClickListener {
            if (checkEmpty()){
        var full_name = add.view?.findViewById<TextView>(R.id.txt_full_name)?.text.toString()
            var service_type = add.view?.findViewById<TextView>(R.id.txt_type_of_service)?.text.toString()
            var city = add.view?.findViewById<TextView>(R.id.txt_city)?.text.toString()
            var phone_number = add.view?.findViewById<TextView>(R.id.txt_contact)?.text.toString()
            var additional_info = add.view?.findViewById<TextView>(R.id.txt_additional_info)?.text.toString()
                var state_view = add.view?.findViewById<TextView>(R.id.state_text_view)

                var info_profile = hashMapOf(
                    "name" to full_name,
                    "city" to city,
                    "service" to service_type,
                    "contact" to phone_number,
                    "additional_info" to additional_info


                )
                db.collection("lifesupport").add(info_profile)
                        .addOnSuccessListener {
                            Toast.makeText(baseContext,  "Your information has successfully been uploaded on the billboard" , Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(baseContext, "There was an error please try again" , Toast.LENGTH_SHORT).show()
                        }
            }
        }
        var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener { 
            when (it.itemId){
                R.id.add_profiles -> makeCurrentFragment(add)
                R.id.add_profiles -> makeSpinner()
                R.id.view_profiles -> makeCurrentFragment(view)

            }
            true
        }
    }


fun makeSpinner(){
    //****spinner****
    var states = add.view?.findViewById<Spinner>(R.id.spinner)

    var stateNames = arrayOf("Andhra Pradesh","Andaman and Nicobar" ,"Arunachal Pradesh", "Assam", "Bihar","Chandigarh", "Chattisgarh","Dadra and Nagar Haveli & Daman and Diu","Delhi",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh","Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala","Ladakh", "Lakhswadeep", "Madhya Pradesh",
            "Maharashtra" , "Manipur" , "Meghalaya" , "Mizoram" , "Nagaland" , "Odisha", "Puducherry","Punjab","Rajasthan", "Sikkim", "Tamil Nadu" , "Telangana" , "Tripura" , "Uttar Pradesh",
            "Uttarakhand" , "West Bengal"
    )

    val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            stateNames
    )



    states?.adapter = adapter

    states?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            selectedState = states?.selectedItem.toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
        //****spinner****
    }
}
    private fun checkEmpty() : Boolean {
        var check = true
        var add = add_profiles()
        var full_name = add.view?.findViewById<TextView>(R.id.txt_full_name)
        var service_type = add.view?.findViewById<TextView>(R.id.txt_type_of_service)
        var city = add.view?.findViewById<TextView>(R.id.txt_city)
        var phone_number = add.view?.findViewById<TextView>(R.id.txt_contact)
        var additional_info = add.view?.findViewById<TextView>(R.id.txt_additional_info)


        if (full_name?.text.toString().isEmpty()){
            full_name?.requestFocus()
            full_name?.error = "Please enter your name"
            check = false
        }
        if (city?.text.toString().isEmpty()){
            city?.requestFocus()
            city?.error = "Please enter your city"
            check = false
        }
        if (service_type?.text.toString().isEmpty()){
            service_type?.requestFocus()
            service_type?.error = "Please enter the type of support you can supply"
            check = false
        }
        if (phone_number?.text.toString().isEmpty()){
            phone_number?.requestFocus()
            phone_number?.error = "Please enter your contact information"
            check = false
        }
        return check
    }
private fun makeCurrentFragment(fragment : Fragment) =
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper , fragment)
        commit()
    }
}

