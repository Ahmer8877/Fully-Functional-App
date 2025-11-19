package com.e.ahmer.navigationviews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchData : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_data)

        supportActionBar?.hide()
        // search data variable

        val searchButton=findViewById<Button>(R.id.btnsearch)
        //Search data Btn after clicking code

        searchButton.setOnClickListener {
            val user=findViewById<TextInputEditText>(R.id.TextUser).text.toString()
            if(user.isEmpty()){
                Toast.makeText(this,"Pela User Name te Likh Wer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            //readata func. code but user existing

            if (user.isNotEmpty()){
                readData(user)
            }else{
                Toast.makeText(this,"Please Enter user Name", Toast.LENGTH_LONG).show()
            }
        }
    }
//    readdata Func.code
    private fun readData(userName: String) {
        database= FirebaseDatabase.getInstance().getReference("StoreData")
        database.child(userName).get().addOnSuccessListener {
            if (it.exists()){

                val email=it.child("email").value
                val password=it.child("password").value
                val userName=it.child("userName").value


                Toast.makeText(this,"Yes is it,Results Found", Toast.LENGTH_SHORT).show()
                val textuser=findViewById<TextInputEditText>(R.id.TextUser)
                textuser.text?.clear()
                val userEmail=findViewById<TextView>(R.id.EmailSearch)
                val passwords=findViewById<TextView>(R.id.PasswordSearch)
                userEmail.text=email.toString()
                passwords.text=password.toString()
            }else{
                Toast.makeText(this,"Results Not Found", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed error in DB", Toast.LENGTH_SHORT).show()

        }
    }
}


