package com.example.triviagame.ui

import kotlin.properties.Delegates

object Constant {
    var categoryMap= mapOf<String,Int>("General Knowledge" to 9,"Sports" to 21, "History" to 23, "Political" to 24, "Arts" to 25 ,"Animals" to 27)

    const val dataName="dataName"
     val result= mutableListOf<Question>()
    val dataResult:List<Question>
    get()= result.toList()
    var questionNumber =1
     var questionCategory:String=""
    var questionDifficulty=""
    const val answerType="multiple"
    fun addResultList(myList:List<Question>){
        myList.forEach{
            addQuestion(it)
        }
    }

    private fun addQuestion(it: Question) {
        result.add(it)
    }
}