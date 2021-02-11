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

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val tv_register: TextView = findViewById(R.id.tv_register)
        tv_register.setOnClickListener{ checkContent() }

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val tv_login_direct: TextView = findViewById(R.id.tv_login_direct)
        tv_login_direct.setOnClickListener { startLogin() }
        //sharedPreferences.edit().clear().commit();
    }

    private fun checkContent(){
        val et_username: EditText = findViewById(R.id.et_username)
        val username: String = et_username.text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()

        }else{
            val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            var users = sharedPreferences.getStringSet("users", HashSet())


            if (users != null) {
                if (!users.contains(username)) {

                    val user = User(username, ArrayList() )
                    val gson = Gson()
                    val userJson = gson.toJson(user)
                    users.add(userJson)

                    var set = HashSet(users)
                    sharedPreferences.edit().putStringSet("users", set).apply()

                    val intent = Intent(this, ChooseYourTopic::class.java)
                    intent.putExtra(QuestionPicker.USER_NAME, username)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up)

                    finish()

                }else {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}