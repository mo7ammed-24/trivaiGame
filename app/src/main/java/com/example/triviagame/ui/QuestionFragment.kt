package com.example.triviagame.ui

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.triviagame.R
import com.example.triviagame.databinding.FragmentQuesstionBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class QuestionFragment:BaseFragment<FragmentQuesstionBinding>() {
    val fragmentAnswer=AnswerFragment()
    var responseString:String?=""
    private val client= OkHttpClient()
    private lateinit var url:String
    lateinit var myGson:Gson
    private fun setSpinnerAdapter() {
        val mAdapter=ArrayAdapter(requireContext(),R.layout.spinner_item,resources.getStringArray(R.array.question_number))
        binding!!.spinnerNoQuestion.apply {
            adapter=mAdapter
        }
        val mAdapter2=ArrayAdapter(requireContext(),R.layout.spinner_item,resources.getStringArray(R.array.question_category))
        binding!!.spinnerCategory.apply {
            adapter=mAdapter2
        }
        val mAdapter3=ArrayAdapter(requireContext(),R.layout.spinner_item,resources.getStringArray(R.array.question_difficulty))
        binding!!.spinnerDifficulty.apply {
            adapter=mAdapter3
        }
    }

    override val LOG_TAG: String ="AnyThing"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuesstionBinding=FragmentQuesstionBinding::inflate

    override fun setup() {
        setSpinnerAdapter()
    }

    override fun addCallbacks() {
        setSpinnerValue()
        binding?.buttonStart?.setOnClickListener {
            startQuestion()
        }
    }

    private fun startQuestion() {
        makeRequest()
    }

    private fun setSpinnerValue() {
        binding?.spinnerNoQuestion?.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Constant.questionNumber=(resources.getStringArray(R.array.question_number)[p2]).toInt() }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Constant.questionNumber=resources.getStringArray(R.array.question_number)[0].toString().toInt()

            }


        }

        binding?.spinnerCategory?.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?,p2: Int,p3: Long) {
                Constant.questionCategory=resources.getStringArray(R.array.question_category)[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Constant.questionCategory=resources.getStringArray(R.array.question_category)[0]
            }

        }

        binding?.spinnerDifficulty?.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?,p2: Int,p3: Long) {
                Constant.questionDifficulty=resources.getStringArray(R.array.question_difficulty)[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Constant.questionDifficulty=resources.getStringArray(R.array.question_difficulty)[0]
            }

        }

    }


    private fun makeRequest() {
        setURL()
        val request= Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("Hamada","${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                        val result=Gson().fromJson(response.body?.string().toString(),QuestionList::class.java)
                        result.results?.let { Constant.addResultList(it)} }
        })
        replaceFragment()
    }

//    private fun getData(result: QuestionList) {
//        Constant.result=result
//        var bundle=Bundle()
//        bundle.putSerializable(Constant.dataName,result)
//        fragmentAnswer.arguments=bundle
//        Thread.sleep(4000)
//    }

    private fun setURL() {
        when {
            Constant.questionDifficulty=="Any Difficulty" && Constant.questionCategory=="Any Category" -> {
                url = "https://opentdb.com/api.php?amount=${Constant.questionNumber}&type=${Constant.answerType}"
            }
            else -> url = if(Constant.questionCategory=="Any Category"){
                "https://opentdb.com/api.php?amount=${Constant.questionNumber}&difficulty=${Constant.questionDifficulty}&type=${Constant.answerType}"
            } else if(Constant.questionDifficulty=="Any Difficulty"){
                "https://opentdb.com/api.php?amount=${Constant.questionNumber}&category=${Constant.categoryMap[Constant.questionCategory]}&type=${Constant.answerType}"
            } else{
                "https://opentdb.com/api.php?amount=${Constant.questionNumber}&category=${Constant.categoryMap[Constant.questionCategory]}&difficulty=${Constant.questionDifficulty}&type=${Constant.answerType}"
            }
        }
    }
    fun replaceFragment(){
        val transaction=parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragmentAnswer)
        transaction.commit()
    }

}