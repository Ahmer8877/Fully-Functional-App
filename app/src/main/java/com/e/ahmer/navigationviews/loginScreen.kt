package com.e.ahmer.navigationviews

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class loginScreen : AppCompatActivity() {

    lateinit var database : DatabaseReference
    lateinit var dialoge : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        //hide uper action bar
        supportActionBar?.hide()

        // Login Button code with intent
        val Logintext=findViewById<TextView>(R.id.textLogin)


        Logintext.setOnClickListener {
            val intent= Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

        // after login button code
        val loginBtn=findViewById<Button>(R.id.btnLogin)
        val mailText=findViewById<TextInputEditText>(R.id.TextEmails)

        //set  the separete dialog code
        dialoge= Dialog(this)
        dialoge.setContentView(R.layout.dialogstyle)
        dialoge.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box))


        //dialoge ok Btn code
        val OKBtn=dialoge.findViewById<Button>(R.id.OKbutton)
        OKBtn.setOnClickListener {
            dialoge.dismiss()
        }




        //login button on click code
        loginBtn.setOnClickListener {
            val userName=mailText.text.toString()

            //check user input is empty or not
            if (userName.isEmpty()){
                Toast.makeText(this,"Please Fill the Activities", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

           //check user input is valid or not
            if (userName.isNotEmpty()){
                readdata(userName)
            }else{
                Toast.makeText(this,"Please enter the activities", Toast.LENGTH_SHORT).show()
            }
        }
    }
      //readdata func. code
    private fun readdata(UserName: String) {

        database= FirebaseDatabase.getInstance().getReference("StoreData")
        database.child(UserName).get().addOnSuccessListener {


            //it exist childs to work code
            if (it.exists()){
                val userName=it.child("UserName").value
                val email=it.child("Email").value
                val password=it.child("Password").value

                Toast.makeText(this,"Welcome to Store", Toast.LENGTH_SHORT).show()

                val storeIntent= Intent(this, MainActivity::class.java)
                startActivity(storeIntent)
                //show dialog code
                dialoge.show()

            }else{
                Toast.makeText(this,"User does not Exist , please Register", Toast.LENGTH_LONG).show()

            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed error in DB", Toast.LENGTH_SHORT).show()

        }
    }
}