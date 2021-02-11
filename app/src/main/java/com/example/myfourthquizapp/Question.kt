package com.example.myfourthquizapp

data class Question(
        val id: Int,
        val questionTxt: String,
        val image: Int,
        val optionOne: String,
        val optionTwo: String,
        val optionThree: String,
        val optionFour: String,
        val correctOption: Int
)
