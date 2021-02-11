package com.example.myfourthquizapp

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AnsweredQuiz (
    @SerializedName("topicId")  val topicId: Int,
    @SerializedName("nCorrectAnswers")  val nCorrectAnswers: Int
    ): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(topicId)
        parcel.writeInt(nCorrectAnswers)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnsweredQuiz> {
        override fun createFromParcel(parcel: Parcel): AnsweredQuiz {
            return AnsweredQuiz(parcel)
        }

        override fun newArray(size: Int): Array<AnsweredQuiz?> {
            return arrayOfNulls(size)
        }
    }
}