package com.example.myfourthquizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Topic1Intro : AppCompatActivity() , View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mTipsList: ArrayList<Tip>? = null
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic1_intro)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        mTipsList = ConstantsTopic1_Tips.getTips()

        setTip()

        val iv_back_home: ImageView = findViewById(R.id.iv_back_login)
        val iv_high_scores: ImageView = findViewById(R.id.iv_high_scores)
        val tv_play_quiz: TextView = findViewById(R.id.tv_play_quiz)
        val cv_next_tip: CardView = findViewById(R.id.cv_next_tip)

        iv_back_home.setOnClickListener(this)
        iv_high_scores.setOnClickListener(this)
        tv_play_quiz.setOnClickListener(this)
        cv_next_tip.setOnClickListener(this)
    }

    private fun setTip() {

        val tip = mTipsList!!.get(mCurrentPosition - 1)

        val tv_topic1_tip_header: TextView = findViewById(R.id.tv_topic1_tip_header)
        val tv_topic1_tip: TextView = findViewById(R.id.tv_topic1_tip)
        val iv_topic1_tip: ImageView = findViewById(R.id.iv_topic1_tip)
        val tv_topic1_tip_closer: TextView = findViewById(R.id.tv_topic1_tip_closer)

        tv_topic1_tip_header.text = tip.tipHeader
        tv_topic1_tip.text = tip.tipText
        iv_topic1_tip.setImageResource(tip.image)

        if (mCurrentPosition == mTipsList!!.size){
            tv_topic1_tip_closer.text = "Try the quiz game"
        }else{
            tv_topic1_tip_closer.text = "Tap for more"
        }
    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.iv_back_login ->{
                val intent_topic1_intro1 = Intent (this, ChooseYourTopic::class.java)
                intent_topic1_intro1.putExtra(QuestionPicker.USER_NAME, mUserName)
                startActivity(intent_topic1_intro1)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }

            R.id.tv_play_quiz ->{
                val intent_topic1_intro2 = Intent (this, QuizQuestionTopic1::class.java)
                intent_topic1_intro2.putExtra(QuestionPicker.USER_NAME, mUserName)
                startActivity(intent_topic1_intro2)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }

            R.id.cv_next_tip ->{
                mCurrentPosition++

                when{
                    mCurrentPosition <= mTipsList!!.size ->{
                        setTip()
                    }else ->{
                        val intent_topic1_intro3 = Intent(this, QuizQuestionTopic1::class.java)
                    startActivity(intent_topic1_intro3)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    }
                }
            }
        }
    }
}