package com.e.ahmer.navigationviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeleteData.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteData : Fragment() {
    lateinit var database: DatabaseReference
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val delete= inflater.inflate(R.layout.fragment_delete_data, container, false)
     //delete btn variable code
        val btndelete=delete.findViewById<Button>(R.id.btnDelete)

        //delete btn click listener code
        btndelete.setOnClickListener {
            val userdelete=delete.findViewById<EditText>(R.id.editTextNamefordelete).text.toString()

            //if activity are activity empty so this code
            if(userdelete.isEmpty() ) {
                Toast.makeText(requireContext(), "Pela sara Data te Likh Wer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
         //if activity are not empty so this code
            if (userdelete.isNotEmpty()){
                deletedata(userdelete)
            }else{
                Toast.makeText(requireContext(),"Please Enter the required Data", Toast.LENGTH_LONG).show()

            }
        }
        return delete

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeleteData.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeleteData().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

    //delete data function code
    private fun deletedata(userdelete: String) {
        database= FirebaseDatabase.getInstance().getReference("StoreData")
        database.child(userdelete).removeValue().addOnSuccessListener {
            Toast.makeText(requireContext(),"Deleted", Toast.LENGTH_LONG).show()
            
            val delete= view?.findViewById<EditText>(R.id.editTextNamefordelete)
            delete?.text?.clear()

        }.addOnFailureListener {
            Toast.makeText(requireContext(),"Unable to Delete", Toast.LENGTH_LONG).show()

        }
    }

}

