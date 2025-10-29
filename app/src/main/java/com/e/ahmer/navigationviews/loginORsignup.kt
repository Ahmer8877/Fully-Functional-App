@file:Suppress("LocalVariableName")

package com.e.ahmer.navigationviews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

@Suppress("ClassName")
class loginORsignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_orsignup)

        //remove action bar code
        supportActionBar?.hide()

        //login and register Btn code with intents
        val loginbtn=findViewById<Button>(R.id.loginbutton)
        val Registerbtn=findViewById<Button>(R.id.registerbutton)

        loginbtn.setOnClickListener {
            val intent= Intent(this, loginScreen::class.java)
            startActivity(intent)
        }
        Registerbtn.setOnClickListener {
            val intent= Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }
    }
}