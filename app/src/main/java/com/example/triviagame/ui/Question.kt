package com.example.triviagame.ui

import com.google.gson.annotations.SerializedName

data class Question(
    val category:String,
    val type:String,
    val difficulty:String,
    val question:String,
    @SerializedName("correct_answer") val correctAnswer:String,
    @SerializedName("incorrect_answers") val incorrectAnswers:List<String>,
    )