package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.TagResponse
import com.dombrothers.android.dumlink.databinding.TagItemLayout2Binding
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagAdapter2: RecyclerView.Adapter<TagViewHolder2>() {
    private val items = TagResponse()

    fun setItemList(newItems: TagResponse) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder2 {
        return TagViewHolder2(
            TagItemLayout2Binding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder2, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}