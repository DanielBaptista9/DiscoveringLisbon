package com.example.myfourthquizapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

       // sharedPreferences.edit().clear().commit();


        val tv_start: TextView = findViewById(R.id.tv_register)
        tv_start.setOnClickListener{ checkEmpty() }

        val tv_register_direct: TextView = findViewById(R.id.tv_register_direct)
        tv_register_direct.setOnClickListener { startRegister() }

    }

    private fun checkEmpty(){
        val et_name: EditText = findViewById(R.id.et_username)

        if (et_name.text.toString().isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()

        }else{

            val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            var users = sharedPreferences.getStringSet("users", HashSet())

            if (users != null) {

                val gson = Gson()
                var found = false

                for (userjson in users) {
                    val user = gson.fromJson(userjson, User::class.java)
                    if (user.username == et_name.text.toString()) {
                        found = true

                        val intent = Intent(this, ChooseYourTopic::class.java)
                        intent.putExtra(QuestionPicker.USER_NAME, et_name.text.toString())
                        //intent.putExtra(QuestionPicker.USER, user)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up)

                        finish()
                        break
                    }
                }
                if (!found){
                Toast.makeText(this, "Username doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startRegister() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
        finish()
    }
}