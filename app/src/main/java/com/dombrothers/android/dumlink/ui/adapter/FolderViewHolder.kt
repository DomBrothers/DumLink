package com.dombrothers.android.dumlink.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding

class FolderViewHolder(
    private val binding: FolderItemLayoutBinding,
    private val listener: (Folder) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Folder) {
        with(binding) {
            Glide.with(itemView.context).load(item.imageUrl)
                .error(R.drawable.ic_folder)
                .placeholder(R.drawable.ic_folder).transform(CenterCrop())
                .into(folderItemImg)

            folderItemTxtFolderName.text = item.folderName
            folderItemTxtLinkCount.text = item.linkCount.toString()

            folderItemImg.setColorFilter(ContextCompat.getColor(itemView.context, R.color.dum_gray03), android.graphics.PorterDuff.Mode.SRC_IN)
            folderItemContainer.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_layout_white)
            folderItemTxtFolderName.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))
            folderItemTxtLinkCount.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}