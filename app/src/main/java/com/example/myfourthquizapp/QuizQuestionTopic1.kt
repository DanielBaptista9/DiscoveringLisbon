package com.example.myfourthquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionTopic1 : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question_topic1)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        mUserName = intent.getStringExtra(QuestionPicker.USER_NAME)

        mQuestionsList = QuestionPicker.getQuestions(applicationContext)

        setQuestion()

        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)
        val tv_submit: TextView = findViewById(R.id.tv_submit)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        tv_submit.setOnClickListener(this)
    }

    private fun setQuestion(){

        val tv_submit: TextView = findViewById(R.id.tv_submit)

        val question = mQuestionsList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size){
            tv_submit.text = "Submit"
        }else{
            tv_submit.text = "Submit"
        }

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_question_topic1: TextView = findViewById(R.id.tv_question_topic1)
        val tv_progress: TextView = findViewById(R.id.tv_progress)
        val iv_image: ImageView = findViewById(R.id.iv_image)
        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)

        progressBar.progress = mCurrentPosition
        progressBar.max = mQuestionsList!!.size
        tv_progress.text = """$mCurrentPosition/${progressBar.max}"""

        tv_question_topic1.text = question.questionTxt
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour

        tv_option_one.isEnabled = true
        tv_option_two.isEnabled = true
        tv_option_three.isEnabled = true
        tv_option_four.isEnabled = true
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.bg_default_option_border)
        }
    }

    override fun onClick(v: View?) {

        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)
        val tv_submit: TextView = findViewById(R.id.tv_submit)

        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.tv_submit ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()

                        }else ->{
                        val intent = Intent(this, ResultActivityTopic1::class.java)
                        intent.putExtra(QuestionPicker.USER_NAME, mUserName)
                        intent.putExtra(QuestionPicker.CORRECT_ANSWERS, mCorrectAnswer)
                        intent.putExtra(QuestionPicker.TOTAL_QUESTIONS, mQuestionsList!!.size)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                    }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition -1)

                    if (question!!.correctOption != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.bg_wrong_option_border)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctOption, R.drawable.bg_correct_option_border)

                    tv_option_one.isEnabled = false
                    tv_option_two.isEnabled = false
                    tv_option_three.isEnabled = false
                    tv_option_four.isEnabled = false

                    if (mCurrentPosition == mQuestionsList!!.size){
                        tv_submit.text = "Finish"
                    }else{
                        tv_submit.text = "Next Question"
                    }
                    mSelectedOptionPosition = 0
                }

            }
        }

    }

    private fun answerView(answer: Int, drawableView: Int){

        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)

        when(answer){
            1 ->{
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 ->{
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 ->{
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 ->{
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun selectedOptionView(textView: TextView, selectedOptionNum: Int){

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.bg_selected_option_border)
    }
}