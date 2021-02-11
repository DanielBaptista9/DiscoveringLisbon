package com.example.myfourthquizapp

import android.content.Context

object QuestionPicker {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    //const val USER: String = "user"

    fun getQuestions(context: Context): ArrayList<Question>{

        val questionsList = ArrayList<Question>()
        val maxNumberQuestions = 4

        //val json = getJsonDataFromAsset(context, "quizzes.json")


        val que1 = Question(
            1,
            "Question 1",
            R.drawable.iv_alc_ponte_25_abril,
            "Alone",
            "Best Party",
            "Dimaggio",
            "Exposed",
            1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,
            "Question 2",
            R.drawable.iv_alc_ponte_25_abril,
            "Alone",
            "Best Party",
            "Dimaggio",
            "Exposed",
            2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,
            "Question 3",
            R.drawable.iv_alc_ponte_25_abril,
            "Alone",
            "Best Party",
            "Dimaggio",
            "Exposed",
            3
        )
        questionsList.add(que3)

        val que4 = Question(
            4,
            "Question 4",
            R.drawable.iv_alc_ponte_25_abril,
            "Alone",
            "Falling too",
            "Dimaggio",
            "Exposed",
            4
        )
        questionsList.add(que4)

        val que5 = Question(
            5,
            "Question 5",
            R.drawable.iv_alc_ponte_25_abril,
            "Falling too",
            "Best Party",
            "Dimaggio",
            "Exposed",
            1
        )
        questionsList.add(que5)




        val selectedQuestions = ArrayList<Question>()

        val questionsListCopy = questionsList.toMutableList() as ArrayList<Question>

        for (i in 1..maxNumberQuestions) {

            val randomNumber = (0 until questionsListCopy.size).random()

            val selectedQuestion = questionsListCopy[randomNumber]

            questionsListCopy.remove(selectedQuestion)

            selectedQuestions.add(selectedQuestion)
        }
        return selectedQuestions
    }

    /*
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
    */
}