package com.e.ahmer.navigationviews

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)

        supportActionBar?.hide()


        //update data variable
        val updatedata=findViewById<Button>(R.id.btnUpdate)

        //update data Btn after clicking code
        updatedata.setOnClickListener {
            val UserInput=findViewById<EditText>(R.id.editTextName).text.toString()
            val UserMail=findViewById<EditText>(R.id.editTextEmail).text.toString()
            val UserPassword=findViewById<EditText>(R.id.editTextPassword).text.toString()

            //if activity are activity empty so this code
            if(UserInput.isEmpty() || UserMail.isEmpty() || UserPassword.isEmpty()){
                Toast.makeText(this,"Pela sara Data te Likh Wer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


            //search func. code but user existing
            if (UserInput.isNotEmpty() || UserMail.isNotEmpty() || UserPassword.isNotEmpty()){
                updateData(UserInput,UserMail,UserPassword)
            }else{
                Toast.makeText(this,"Please Enter the Activities", Toast.LENGTH_LONG).show()

            }

            }
    }
    //search func. code
    private fun updateData(UserInput: String, UserMail: String, UserPassword: String) {
        database= FirebaseDatabase.getInstance().getReference("StoreData")
        //update data in firebase database
        val Updata=mapOf<String, String>("userName" to UserInput,"email" to UserMail,"password" to UserPassword)

        //update data in firebase database
        database.child(UserInput).updateChildren(Updata).addOnSuccessListener {

            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()
            //clear edit text
            findViewById<EditText>(R.id.editTextName).text.clear()
            findViewById<EditText>(R.id.editTextEmail).text.clear()
            findViewById<EditText>(R.id.editTextPassword).text.clear()
            //finish activity

            //if data not update this code
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }

}

