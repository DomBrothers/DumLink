package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding
import kotlin.reflect.KFunction1


class FolderAdapter(private val listener: (Folder) -> Unit) :
    RecyclerView.Adapter<FolderViewHolder>() {
    private val items = ArrayList<Folder>()

    fun setItemList(newItems: List<Folder>) {
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

    override fun getItemCount() = items.size
}