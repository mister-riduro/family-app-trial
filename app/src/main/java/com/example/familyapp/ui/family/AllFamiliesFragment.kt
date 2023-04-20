package com.example.familyapp.ui.family

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyapp.databinding.FragmentAllFamiliesBinding
import com.example.familyapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFamiliesFragment : Fragment() {

    private var binding: FragmentAllFamiliesBinding? = null
    private lateinit var allFamiliesAdapter: AllFamiliesAdapter
    private val allFamiliesViewModel: AllFamiliesViewModel by viewModels()

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

        setupRecyclerView()

        allFamiliesViewModel.getAllFamilies()
        allFamiliesViewModel._allFamiliesLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    allFamiliesAdapter.differ.submitList(response.data?.familiesItems)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        allFamiliesAdapter = AllFamiliesAdapter()
        binding!!.rvListKeluarga.apply {
            adapter = allFamiliesAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}