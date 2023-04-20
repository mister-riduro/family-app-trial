package com.example.familyapp.ui.family

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyapp.databinding.ActivityDetailFamilyBinding
import com.example.familyapp.util.Resource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFamilyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFamilyBinding
    private val myFamilyViewModel: MyFamilyViewModel by viewModels()
    private var familyMember: Int? = 0
    private var familyID: String = ""
    private lateinit var familyMemberListAdapter: FamilyMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFamilyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        familyID = intent.getStringExtra("FAMILY_ID")!!

        supportActionBar?.hide()
        setupRecyclerView()

        binding.layoutBack.setOnClickListener {
            onBackPressed()
        }

        myFamilyViewModel.getRolesByFamily(familyID)
        myFamilyViewModel._roleLiveData.observe(this) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    familyMember = response.data?.rolesItems?.size
                    familyMemberListAdapter.differ.submitList(response.data?.rolesItems)
                }
                else -> {

                }
            }
        }

        myFamilyViewModel.getFamilyDetail(familyID)
        myFamilyViewModel._familyLiveData.observe(this) { response ->
            when(response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val image = "https://pocket-first.pockethost.io/api/files/lo7odjdjz2wdeay/${response.data?.id}/${response.data?.familyPhoto}?token="
                    Picasso.get().load(image).into(binding.ivFamilyPhoto)
                    binding.tvFamilyName.text = response.data?.familyName
                    binding.tvFamilyDomicile.text = response.data?.domicile
                    binding.tvFamilyMember.text = familyMember.toString() + " Anggota"
                }
                else -> {

                }
            }
        }
    }

    private fun setupRecyclerView() {
        familyMemberListAdapter = FamilyMemberAdapter()
        binding.rvFamilyMember.apply {
            adapter = familyMemberListAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }
}