package com.dombrothers.android.dumlink.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagViewHolder(private val binding: TagItemLayoutBinding, private val listener: (String) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tagItemTxt.text = item
            itemView.setOnClickListener { listener(item) }
        }
    }