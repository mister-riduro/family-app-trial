package com.example.familyapp.ui.family

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyapp.data.preferences.Preferences
import com.example.familyapp.databinding.FragmentMyFamilyBinding
import com.example.familyapp.util.Resource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFamilyFragment : Fragment() {

    private var binding: FragmentMyFamilyBinding? = null
    private val myFamilyViewModel: MyFamilyViewModel by viewModels()
    private var familyMember: Int? = 0
    private lateinit var familyMemberListAdapter: FamilyMemberAdapter
    private val familyID: String = Preferences.instance.familyID.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyFamilyBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        if (familyID.isEmpty()) {
            binding?.layoutDataExist?.visibility = View.GONE
            binding?.layoutNoData?.visibility = View.VISIBLE
        } else {
            myFamilyViewModel.getRolesByFamily(familyID)
            myFamilyViewModel._roleLiveData.observe(viewLifecycleOwner) { response ->
                when(response) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        familyMember = response.data?.rolesItems?.size
                        familyMemberListAdapter.differ.submitList(response.data?.rolesItems)
                    }
                }
            }

            myFamilyViewModel.getFamilyDetail(familyID)
            myFamilyViewModel._familyLiveData.observe(viewLifecycleOwner) { response ->
                when(response) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val image = "https://pocket-first.pockethost.io/api/files/lo7odjdjz2wdeay/${response.data?.id}/${response.data?.familyPhoto}?token="
                        Picasso.get().load(image).into(binding?.ivFamilyPhoto)
                        binding?.tvFamilyName?.text = response.data?.familyName
                        binding?.tvFamilyDomicile?.text = response.data?.domicile
                        binding?.tvFamilyMember?.text = familyMember.toString() + " Anggota"
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        familyMemberListAdapter = FamilyMemberAdapter()
        binding!!.rvFamilyMember.apply {
            adapter = familyMemberListAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}