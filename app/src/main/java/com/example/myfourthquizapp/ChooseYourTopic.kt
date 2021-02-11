package com.example.myfourthquizapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChooseYourTopic : AppCompatActivity() , View.OnClickListener {

    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_your_topic)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        mUserName = intent.getStringExtra(QuestionPicker.USER_NAME)

        loadData()

        val tv_topic1: TextView = findViewById(R.id.tv_topic1)
        val tv_topic2: TextView = findViewById(R.id.tv_topic2)
        val tv_topic3: TextView = findViewById(R.id.tv_topic3)
        val tv_topic4: TextView = findViewById(R.id.tv_topic4)
        val tv_topic5: TextView = findViewById(R.id.tv_topic5)
        val tv_topic6: TextView = findViewById(R.id.tv_topic6)
        val tv_topic7: TextView = findViewById(R.id.tv_topic7)
        val tv_topic8: TextView = findViewById(R.id.tv_topic8)
        val tv_topic9: TextView = findViewById(R.id.tv_topic9)
        val tv_topic10: TextView = findViewById(R.id.tv_topic10)
        val tv_topic11: TextView = findViewById(R.id.tv_topic11)
        val tv_topic12: TextView = findViewById(R.id.tv_topic12)
        val iv_back_login: ImageView = findViewById(R.id.iv_back_login)

        tv_topic1.setOnClickListener(this)
        tv_topic2.setOnClickListener(this)
        tv_topic3.setOnClickListener(this)
        tv_topic4.setOnClickListener(this)
        tv_topic5.setOnClickListener(this)
        tv_topic6.setOnClickListener(this)
        tv_topic7.setOnClickListener(this)
        tv_topic8.setOnClickListener(this)
        tv_topic9.setOnClickListener(this)
        tv_topic10.setOnClickListener(this)
        tv_topic11.setOnClickListener(this)
        tv_topic12.setOnClickListener(this)
        iv_back_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.tv_topic1 ->{
                val intent_topic1 = Intent (this, Topic1Intro::class.java)
                intent_topic1.putExtra(QuestionPicker.USER_NAME, mUserName)
                startActivity(intent_topic1)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }

            R.id.iv_back_login ->{
                val intent_back_home = Intent (this, MainActivity::class.java)
                intent_back_home.putExtra(QuestionPicker.USER_NAME, mUserName)
                startActivity(intent_back_home)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedCorrectAnswers: Int = sharedPreferences.getInt("CorrectAnswers", 0)
        val savedTotalQuestions: Int = sharedPreferences.getInt("TotalQuestions", 0)

        val tv_high_score_topic1: TextView = findViewById(R.id.tv_high_score_topic1)
        tv_high_score_topic1.text="$savedCorrectAnswers/$savedTotalQuestions"
    }
}