package com.example.triviagame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.triviagame.R
import com.example.triviagame.databinding.FragmentQuesstionBinding

class QuestionFragment:BaseFragment<FragmentQuesstionBinding>() {

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

    }
}