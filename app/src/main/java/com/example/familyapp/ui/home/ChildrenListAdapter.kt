package com.example.familyapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.familyapp.data.models.ancestor.AncestorsItem
import com.example.familyapp.data.preferences.Preferences
import com.example.familyapp.databinding.ItemMbahChildrenBinding
import com.squareup.picasso.Picasso

class ChildrenListAdapter(): RecyclerView.Adapter<ChildrenListAdapter.ChildrenListViewHolder>() {
    inner class ChildrenListViewHolder(private val binding: ItemMbahChildrenBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemMbahChildrenBinding

    private val differCallback = object : DiffUtil.ItemCallback<AncestorsItem>() {
        override fun areItemsTheSame(oldItem: AncestorsItem, newItem: AncestorsItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AncestorsItem, newItem: AncestorsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenListViewHolder {
        binding = ItemMbahChildrenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildrenListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ChildrenListViewHolder, position: Int) {
        val ancestor = differ.currentList[position]
        holder.itemView.apply {
            val image = "https://pocket-first.pockethost.io/api/files/_pb_users_auth_/${ancestor.userId}/" + ancestor.expand.userId.avatar + "?token="
            Picasso.get().load(image).into(binding.ivAvatar)
            binding.tvUsername.text = ancestor.expand.userId.name
            binding.tvTag.text = ancestor.expand.userId.additionalDesc
            binding.tvTotalChildren.text = ancestor.expand.userId.childId.size.toString()
        }
    }

}