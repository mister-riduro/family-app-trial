package com.example.familyapp.ui.family

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.familyapp.R
import com.example.familyapp.data.models.role.RolesItem
import com.example.familyapp.data.preferences.Preferences
import com.example.familyapp.databinding.ItemFamilyMemberBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

class FamilyMemberAdapter(): RecyclerView.Adapter<FamilyMemberAdapter.FamilyMemberViewHolder>() {
    inner class FamilyMemberViewHolder(private val binding: ItemFamilyMemberBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var binding: ItemFamilyMemberBinding

    private val differCallback = object : DiffUtil.ItemCallback<RolesItem>() {
        override fun areItemsTheSame(
            oldItem: RolesItem,
            newItem: RolesItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RolesItem,
            newItem: RolesItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyMemberViewHolder {
        binding = ItemFamilyMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FamilyMemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FamilyMemberViewHolder, position: Int) {
        val familyMember = differ.currentList[position]
        holder.itemView.apply {
            val image = "https://pocket-first.pockethost.io/api/files/_pb_users_auth_/${familyMember.userId}/${familyMember.rolesExpand.userId.avatar}?token="
            Picasso.get().load(image).into(binding.ivAvatar)
            binding.tvTag.text = familyMember.role
            binding.tvUsername.text = familyMember.rolesExpand.userId.name

            setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(holder.itemView.context)
                val bottomSheetView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.layout_dialog_detail_user, null)

                val imageView = bottomSheetView.findViewById<ImageView>(R.id.iv_avatar)
                Picasso.get().load(image).into(imageView)

                bottomSheetView.findViewById<TextView>(R.id.tv_username).text = familyMember.rolesExpand.userId.name

                if (familyMember.role.isEmpty()) {
                    bottomSheetView.findViewById<TextView>(R.id.tv_tag).text = "Data Kosong"
                } else {
                    bottomSheetView.findViewById<TextView>(R.id.tv_tag).text = familyMember.role
                }

                if (familyMember.rolesExpand.userId.fatherId.isEmpty()) {
                    bottomSheetView.findViewById<TextView>(R.id.tv_ayah).text = "Data Kosong"
                } else {
                    bottomSheetView.findViewById<TextView>(R.id.tv_ayah).text = familyMember.rolesExpand.userId.expand.fatherID.name
                }

                if(familyMember.rolesExpand.userId.motherId.isEmpty()) {
                    bottomSheetView.findViewById<TextView>(R.id.tv_ibu).text = "Data Kosong"
                } else {
                    bottomSheetView.findViewById<TextView>(R.id.tv_ibu).text = familyMember.rolesExpand.userId.expand.motherID.name
                }

                bottomSheetView.findViewById<TextView>(R.id.tv_status).text = familyMember.rolesExpand.userId.status

                if(familyMember.rolesExpand.userId.phoneNumber.isEmpty()) {
                    bottomSheetView.findViewById<TextView>(R.id.tv_nomor_telepon).text = "Tidak ada nomor telepon"
                } else {
                    bottomSheetView.findViewById<TextView>(R.id.tv_nomor_telepon).text = familyMember.rolesExpand.userId.phoneNumber
                }

                bottomSheetView.findViewById<TextView>(R.id.tv_tanggal_lahir).text = familyMember.rolesExpand.userId.bornDate

                if(familyMember.rolesExpand.userId.dateDead.isEmpty()) {
                    bottomSheetView.findViewById<View>(R.id.layout_died_date).visibility = View.GONE
                } else {
                    bottomSheetView.findViewById<View>(R.id.layout_died_date).visibility = View.VISIBLE
                    bottomSheetView.findViewById<TextView>(R.id.tv_tanggal_meninggal).text = familyMember.rolesExpand.userId.dateDead
                }

                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
                bottomSheetView.findViewById<View>(R.id.iv_icon_close).setOnClickListener {
                    bottomSheetDialog.dismiss()
                }
            }
        }
    }

}