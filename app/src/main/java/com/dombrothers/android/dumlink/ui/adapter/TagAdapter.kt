package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.TagResponse
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagAdapter(private val listener: (String) -> Unit, val isMain: Boolean = false): RecyclerView.Adapter<TagViewHolder>() {
    private val items = TagResponse()

    fun setItemList(newItems: TagResponse) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            TagItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return if (isMain && items.size > 8) {
            8
        } else {
            items.size
        }
    }
}