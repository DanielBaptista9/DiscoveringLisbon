package com.example.myfourthquizapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivityTopic1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_topic1)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val tv_name: TextView = findViewById(R.id.tv_name)
        val tv_score: TextView = findViewById(R.id.tv_score)
        val tv_finish: TextView = findViewById(R.id.tv_finish)

        val  username = intent.getStringExtra(QuestionPicker.USER_NAME)
        tv_name.text = username

        val totalQuestions = intent.getIntExtra(QuestionPicker.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(QuestionPicker.CORRECT_ANSWERS, 0)

        tv_score.text = "Your Score is $correctAnswers out of $totalQuestions"

        tv_finish.setOnClickListener{
            startActivity(Intent(this, ChooseYourTopic::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedCorrectAnswers: Int = sharedPreferences.getInt("CorrectAnswers", 0)
        val savedTotalQuestions: Int = sharedPreferences.getInt("TotalQuestions", 0)


        if (savedCorrectAnswers < correctAnswers) {
            sharedPreferences.edit().putInt("CorrectAnswers", correctAnswers).apply()

        }


        if (savedTotalQuestions == 0 || savedTotalQuestions!=totalQuestions) {
            sharedPreferences.edit().putInt("TotalQuestions", totalQuestions).apply()
        }
    }
}