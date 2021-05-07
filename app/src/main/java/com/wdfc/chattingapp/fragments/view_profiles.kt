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

// TODO: Rename parameter arguments, choose names that match

class view_profiles : Fragment(R.layout.fragment_view_profiles) {
    var selected_sort = ""
    var items = ArrayList<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore


        // **********views initialisation************
        var sort_spinner = activity?.findViewById<Spinner>(R.id.spinner3)
        var list_view = activity?.findViewById<ListView>(R.id.list_view_profiles)
        var search = activity?.findViewById<Button>(R.id.btn_search)
        //***********views initialisation************


        //******spinner*********
        var stateNames = arrayOf("Andhra Pradesh","Andaman and Nicobar" ,"Arunachal Pradesh", "Assam", "Bihar","Chandigarh", "Chattisgarh","Dadra and Nagar Haveli & Daman and Diu","Delhi",
                "Goa", "Gujarat", "Haryana", "Himachal Pradesh","Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala","Ladakh", "Lakhswadeep", "Madhya Pradesh",
                "Maharashtra" , "Manipur" , "Meghalaya" , "Mizoram" , "Nagaland" , "Odisha", "Puducherry","Punjab","Rajasthan", "Sikkim", "Tamil Nadu" , "Telangana" , "Tripura" , "Uttar Pradesh",
                "Uttarakhand" , "West Bengal"
        )
        val spinner_adapter = activity?.let {
            ArrayAdapter<String>(
                    it,
                android.R.layout.simple_spinner_dropdown_item,
                stateNames
        )
        }
        sort_spinner?.adapter = spinner_adapter
        sort_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selected_sort = sort_spinner?.selectedItem.toString()
                var thiefs = db.collection("lifesupport").whereEqualTo("state" , selected_sort).get()
                        .addOnSuccessListener { document ->
                            for ( i in document){
                                items.add("name: " + i.get("name").toString() + "\ncontact: " + i.get("contact")+"\ncity: " + i.get("city") + "\nservice: " + i.get("service") + "\nadditional_info: " + i.get("additional_info"))


                            }

                            var list_array_adapter = activity?.let { it1 -> ArrayAdapter<String>(it1, android.R.layout.simple_list_item_1 , items) }
                            list_view?.adapter = list_array_adapter
                            items = ArrayList()
                        }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
            //******spinner*********

            search?.setOnClickListener {
                var thiefs = db.collection("lifesupport").whereEqualTo("state" , selected_sort).get()
                        .addOnSuccessListener { document ->
                            for ( i in document){
                                items.add("name: " + i.get("name").toString() + "\ncontact: " + i.get("contact")+"\ncity: " + i.get("city") + "\nservice: " + i.get("service") + "\nadditional_info: " + i.get("additional_info"))


                            }
                            
                            var list_array_adapter = activity?.let { it1 -> ArrayAdapter<String>(it1, android.R.layout.simple_list_item_1 , items) }
                            list_view?.adapter = list_array_adapter
                            items = ArrayList()
            }



                var t2 = db.collection("lifesupport").whereEqualTo("state" , selected_sort).get()
                        .addOnSuccessListener { document ->
                            for ( i in document){
                                items.add("name: " + i.get("name").toString() + "\ncontact: " + i.get("contact")+"\ncity: " + i.get("city") + "\nservice: " + i.get("service") + "\nadditional_info: " + i.get("additional_info"))


                            }

                            var list_array_adapter = activity?.let { it1 -> ArrayAdapter<String>(it1, android.R.layout.simple_list_item_1 , items) }
                            list_view?.adapter = list_array_adapter
                            items = ArrayList()






}}}}