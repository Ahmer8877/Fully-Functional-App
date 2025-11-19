package com.e.ahmer.navigationviews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterScreen : AppCompatActivity() {
    lateinit var database : DatabaseReference
    @Suppress("LocalVariableName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        //remove action bar code
        supportActionBar?.hide()

        //login text onclick
        val Registertext=findViewById<TextView>(R.id.textRegister)
        Registertext.setOnClickListener {
            val intent= Intent(this, loginScreen::class.java)
            startActivity(intent)
            finish()
        }
        //register button onclick and firebase data
        val registerBtn=findViewById<Button>(R.id.btnRegister)
        val edittext=findViewById<TextInputEditText>(R.id.editTextName)
        val edittextemail=findViewById<TextInputEditText>(R.id.editTextEmail)
        val inputpassword=findViewById<TextInputEditText>(R.id.editTextPassword)

        //when some input is empty show errors
        when{
            edittext.text.toString().isEmpty() -> {
                edittext.error="Please Enter Name"
                edittext.requestFocus()
            }
            edittextemail.text.toString().isEmpty() -> {
                edittextemail.error="Please Enter Email"
                edittextemail.requestFocus()
            }
            inputpassword.text.toString().isEmpty() -> {
                inputpassword.error="Please Enter Password"
                edittextemail.requestFocus()
            }else -> {
                Toast.makeText(this,"Register Successfully", Toast.LENGTH_SHORT).show()
            }
        }
        //register button onclick

        registerBtn.setOnClickListener {
            val userName=edittext.text.toString()
            val email=edittextemail.text.toString()
            val password=inputpassword.text.toString()

            //if any input section is empty that occur the condition (code)
            if (userName.isEmpty() ||email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Fill the Activities", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //progress bar code(visible)
            val progressBar=findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility= View.VISIBLE
            // Simulate a network delay (like Firebase login)

            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                registerBtn.isEnabled = true


            }, 3000)

            database= FirebaseDatabase.getInstance().getReference("StoreData")
            val User= Firebasedata(userName,email,password)
            database.child(userName).setValue(User).addOnSuccessListener {
                edittextemail.text?.clear()
                inputpassword.text?.clear()

                Toast.makeText(this,"You Registerd,Go to Login Page", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, loginScreen::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"You Failed to Register", Toast.LENGTH_SHORT).show()
            }

        }

    }
}