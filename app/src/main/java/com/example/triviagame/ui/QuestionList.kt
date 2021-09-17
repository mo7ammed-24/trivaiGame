package com.example.triviagame.ui

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QuestionList(
    @SerializedName("response_code") val responseCode:Int,
    val results:List<Question>
) : Serializable
