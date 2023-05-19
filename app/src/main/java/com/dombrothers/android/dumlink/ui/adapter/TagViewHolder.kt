package com.dombrothers.android.dumlink.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Tag
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagViewHolder(private val binding: TagItemLayoutBinding, private val listener: (Tag) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tag) {
            binding.tagItemTxt.text = item.tagName
            itemView.setOnClickListener { listener(item) }
        }
    }