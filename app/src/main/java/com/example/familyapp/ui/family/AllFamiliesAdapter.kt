package com.example.familyapp.ui.family

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.familyapp.data.models.family.FamiliesItem
import com.example.familyapp.databinding.ItemFamilyCardBinding
import com.squareup.picasso.Picasso

class AllFamiliesAdapter(): RecyclerView.Adapter<AllFamiliesAdapter.AllFamiliesViewHolder>() {
    inner class AllFamiliesViewHolder(private val binding: ItemFamilyCardBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFamilyCardBinding

    private val differCallback = object : DiffUtil.ItemCallback<FamiliesItem>() {
        override fun areItemsTheSame(
            oldItem: FamiliesItem,
            newItem: FamiliesItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FamiliesItem,
            newItem: FamiliesItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllFamiliesViewHolder {
        binding = ItemFamilyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllFamiliesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AllFamiliesViewHolder, position: Int) {
        val families = differ.currentList[position]
        holder.itemView.apply {
            val image = "https://pocket-first.pockethost.io/api/files/lo7odjdjz2wdeay/${families.id}/${families.familyPhoto}?token="
            Picasso.get().load(image).into(binding.ivFamilyPhoto)
            binding.tvFamilyName.text = families.familyName
            binding.tvDomicile.text = families.domicile

            setOnClickListener {
                val intent = Intent(it.context, DetailFamilyActivity::class.java)
                intent.putExtra("FAMILY_ID", families.id)
                it.context.startActivity(intent)
            }
        }
    }

}