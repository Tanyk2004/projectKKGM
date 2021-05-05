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
        //****spinner****
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
        var submit =  activity?.findViewById<Button>(R.id.btn_add)
        submit?.setOnClickListener{
            var name = activity?.findViewById<TextView>(R.id.txt_full_name)?.text.toString()
            Toast.makeText(context,  selectedState , Toast.LENGTH_SHORT).show()
        }

    }



}