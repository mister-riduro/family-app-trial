package com.example.familyapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyapp.databinding.FragmentHomeBinding
import com.example.familyapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import com.example.familyapp.data.preferences.Preferences

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var childrenListAdapter: ChildrenListAdapter
    private val homeViewModel: HomeViewModel by viewModels()

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding?.tvUserName?.text = Preferences.instance.userName

        homeViewModel.getAncestorsData()
        homeViewModel._ancestorsLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Error -> {
                    Log.d("ERROR ANCESTORS", "Error get Ancestors Data")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    childrenListAdapter.differ.submitList(response.data?.ancestorsItems)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        childrenListAdapter = ChildrenListAdapter()
        binding!!.rvSumosariChildren.apply {
            adapter = childrenListAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}