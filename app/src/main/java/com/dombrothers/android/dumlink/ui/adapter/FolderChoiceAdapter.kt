package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.FolderChoiceItemLayoutBinding


class FolderChoiceAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<FolderChoiceViewHolder>() {
    private val items = ArrayList<Folder>()

    fun setItemList(newItems: List<Folder>) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderChoiceViewHolder {
        return FolderChoiceViewHolder(
            FolderChoiceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: FolderChoiceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}