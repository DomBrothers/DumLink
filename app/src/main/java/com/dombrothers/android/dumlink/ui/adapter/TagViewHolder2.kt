package com.dombrothers.android.dumlink.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.databinding.TagItemLayout2Binding
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagViewHolder2(private val binding: TagItemLayout2Binding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.tagItemTxt.text = "#$item"
        }
    }