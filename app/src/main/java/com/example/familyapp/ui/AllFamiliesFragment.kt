package com.example.familyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.familyapp.R
import com.example.familyapp.databinding.FragmentAllFamiliesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFamiliesFragment : Fragment() {

    private var binding: FragmentAllFamiliesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllFamiliesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}