package com.wdfc.chattingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wdfc.chattingapp.R



class add_profiles : Fragment(R.layout.fragment_add_profiles) {
    // TODO: Rename and change types of parameters
    var selectedState = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db =  Firebase.firestore

        //***initialisation of views**********
        var name = activity?.findViewById<TextView>(R.id.txt_full_name)
        var city = activity?.findViewById<TextView>(R.id.txt_city)
        var service = activity?.findViewById<TextView>(R.id.txt_type_of_service)
        var phone = activity?.findViewById<TextView>(R.id.txt_contact)
        var additional_information = activity?.findViewById<TextView>(R.id.txt_additional_info)
        var submit =  activity?.findViewById<Button>(R.id.btn_add)
        //***initialisation of views**********


        //********spinner*********
        var states = activity?.findViewById<Spinner>(R.id.spinner)

        var stateNames = arrayOf("Andhra Pradesh","Andaman and Nicobar" ,"Arunachal Pradesh", "Assam", "Bihar","Chandigarh", "Chattisgarh","Dadra and Nagar Haveli & Daman and Diu","Delhi",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh","Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala","Ladakh", "Lakhswadeep", "Madhya Pradesh",
            "Maharashtra" , "Manipur" , "Meghalaya" , "Mizoram" , "Nagaland" , "Odisha", "Puducherry","Punjab","Rajasthan", "Sikkim", "Tamil Nadu" , "Telangana" , "Tripura" , "Uttar Pradesh",
            "Uttarakhand" , "West Bengal"
        )

        val adapter = this.activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                stateNames
            )
        }



        states?.adapter = adapter

        states?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedState = states?.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            //****spinner****


        }


            submit?.setOnClickListener{

            var info_profile = hashMapOf(
                    "state" to selectedState,
                    "name" to name?.text.toString(),
                    "city" to city?.text.toString().trim().toLowerCase(),
                    "service" to service?.text.toString(),
                    "additional_info" to additional_information?.text.toString()


            )
            db.collection("lifesupport").add(info_profile)
                    .addOnSuccessListener {
                        Toast.makeText(activity,  "Your information has successfully been uploaded on the billboard" , Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(activity, "There was an error please try again" , Toast.LENGTH_SHORT).show()
                    }
            Toast.makeText(context,  selectedState , Toast.LENGTH_SHORT).show()

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

}