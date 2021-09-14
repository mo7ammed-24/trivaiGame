package com.example.triviagame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.triviagame.R
import com.example.triviagame.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    val questionFagmentObject=QuestionFragment()
    lateinit var binding:ActivityMainBinding
    lateinit var result:QuestionList
    private val client=OkHttpClient()
    private val url="https://opentdb.com/api.php?amount=10&category=10&difficulty=easy&type=multiple"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeRequest()
        addCallbacks()
    }

    private fun addCallbacks() {
        binding.buttonGo.setOnClickListener {
            addFragment(questionFagmentObject)
        }
    }

    private fun addFragment(fragment:Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,fragment)
        transaction.commit()
            binding.buttonGo.visibility = View.GONE
            binding.textWelcome.visibility=View.GONE
    }

    private fun makeRequest() {
        val request=Request.Builder().url(url).build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                     result=Gson().fromJson(jsonString,QuestionList::class.java)
                }
            }

        })
    }

}