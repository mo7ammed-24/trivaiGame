package com.example.triviagame.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.triviagame.databinding.FragmentAnswerBinding

class AnswerFragment:Fragment(){
    lateinit var binding:FragmentAnswerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAnswerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i("Hamada",Constant.result.toString())
        binding.textHello.text=Constant.result[0].correctAnswer
    }
    }


