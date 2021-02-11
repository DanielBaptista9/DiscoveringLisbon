package com.example.myfourthquizapp

object ConstantsTopic1_Tips {

    fun getTips(): ArrayList<Tip>{

        val tipList = ArrayList<Tip>()
        val maxNumberTips = 3

        val tip1 = Tip(
            1,
            R.drawable.iv_alc_ponte_25_abril,
            "Here's a tip for you:",
            "Be cool"

        )
        tipList.add(tip1)

        val tip2 = Tip(
            2,
            R.drawable.iv_alc_ponte_25_abril,
            "Here's a tip for you:",
            "Be awesome"

        )
        tipList.add(tip2)

        val tip3 = Tip(
            3,
            R.drawable.iv_alc_ponte_25_abril,
            "Here's a tip for you:",
            "Be nice"

        )
        tipList.add(tip3)

        val tip4 = Tip(
            4,
            R.drawable.iv_alc_ponte_25_abril,
            "Here's a tip for you:",
            "Be fantastic"

        )
        tipList.add(tip4)



        val selectedTips = ArrayList<Tip>()

        val tipListCopy = tipList.toMutableList() as ArrayList<Tip>

        for (i in 1..maxNumberTips) {

            val randomNumber = (0 until tipListCopy.size).random()

            val selectedTip = tipListCopy.get(randomNumber)

            tipListCopy.remove(selectedTip)

            selectedTips.add(selectedTip)
        }
        return selectedTips
    }

}