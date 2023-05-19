package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Tag
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding
import com.dombrothers.android.dumlink.databinding.TagItemLayoutBinding

class TagAdapter(private val listener: (Tag) -> Unit): RecyclerView.Adapter<TagViewHolder>() {
    private val items = ArrayList<Tag>()

    fun setItemList(newItems: List<Tag>) {
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

    override fun getItemCount() = items.size
}