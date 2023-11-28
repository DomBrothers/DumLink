package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.databinding.FolderChoiceItemLayoutBinding


class FolderChoiceAdapter(private val listener: (FolderResponseItem) -> Unit) :
    RecyclerView.Adapter<FolderChoiceViewHolder>() {
    private val items = FolderResponse()

    fun setItemList(newItems: FolderResponse) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }


    fun choiceFolder(folderId: Int) {
        items.forEach {
            it.isChecked = folderId == it.folderId
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