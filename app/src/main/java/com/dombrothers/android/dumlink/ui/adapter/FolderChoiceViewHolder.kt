package com.dombrothers.android.dumlink.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.FolderChoiceItemLayoutBinding

class FolderChoiceViewHolder(
    private val binding: FolderChoiceItemLayoutBinding,
    private val listener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Folder) {
        with(binding) {
            folderChoiceItemTxtFolderName.text = item.folderName
            folderChoiceItemTxtLinkCount.text = item.linkCount.toString()
            folderChoiceItemContainer.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_layout_white)
            folderChoiceItemTxtFolderName.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))
            folderChoiceItemTxtLinkCount.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))

            folderChoiceItemCheck.setOnClickListener {
                item.isChecked = folderChoiceItemCheck.isChecked
                listener(adapterPosition)
            }
        }
    }
}