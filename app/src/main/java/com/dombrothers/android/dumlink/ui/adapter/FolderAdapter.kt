package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding


class FolderAdapter(
    private val listener: (FolderResponseItem) -> Unit,
    val isMain: Boolean = false
) :
    RecyclerView.Adapter<FolderViewHolder>() {
    private val items = ArrayList<FolderResponseItem>()

    fun setItemList(newItems: List<FolderResponseItem>) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            FolderItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return if (isMain && items.size > 4) {
            4
        } else {
            items.size
        }
    }
}