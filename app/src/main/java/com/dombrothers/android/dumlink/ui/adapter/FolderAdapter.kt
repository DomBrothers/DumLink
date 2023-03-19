package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding


class FolderAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<FolderViewHolder>() {
    private val items = ArrayList<Folder>()

    fun setItemList(newItems: List<Folder>) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    private fun selectFolder(position: Int) {
        items[position].isSelected = true

        for (i in 0..items.size.minus(1)) {
            if (i != position) items[i].isSelected = false
        }

        notifyDataSetChanged()

        listener(items[position].folderName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            FolderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            selectFolder(position)
        }
    }

    override fun getItemCount() = items.size
}