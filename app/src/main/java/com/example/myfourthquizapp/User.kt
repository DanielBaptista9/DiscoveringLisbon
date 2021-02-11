package com.example.myfourthquizapp

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("username") val username: String?,
        @SerializedName("correctAnswers") val correctAnswers: ArrayList<AnsweredQuiz>
    ): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("correctAnswers")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
