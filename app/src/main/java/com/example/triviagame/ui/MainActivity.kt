package com.example.triviagame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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



}