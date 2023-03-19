package com.dombrothers.android.dumlink.ui.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.FolderItemLayoutBinding
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding

class FolderViewHolder(
    private val binding: FolderItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Folder) {
        with(binding) {
            if (item.isMainFolder) {
                Glide.with(itemView.context).load(item.imageUrl)
                    .error(R.drawable.ic_main_folder)
                    .placeholder(R.drawable.ic_main_folder).transform(CenterCrop())
                    .into(folderItemImg)
            } else {
                Glide.with(itemView.context).load(item.imageUrl)
                    .error(R.drawable.ic_folder)
                    .placeholder(R.drawable.ic_folder).transform(CenterCrop())
                    .into(folderItemImg)
            }

            folderItemTxtFolderName.text = item.folderName

            folderItemTxtLinkCount.text = item.linkCount.toString()

            if (item.isSelected) {
                folderItemImg.setColorFilter(ContextCompat.getColor(itemView.context, R.color.dum_blue03), android.graphics.PorterDuff.Mode.SRC_IN)
                folderItemContainer.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_layout_dum_blue01)
                folderItemTxtFolderName.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_blue03))
                folderItemTxtLinkCount.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_blue03))
            } else {
                folderItemImg.setColorFilter(ContextCompat.getColor(itemView.context, R.color.dum_gray03), android.graphics.PorterDuff.Mode.SRC_IN)
                folderItemContainer.background = ContextCompat.getDrawable(itemView.context, R.drawable.bg_layout_white)
                folderItemTxtFolderName.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))
                folderItemTxtLinkCount.setTextColor(ContextCompat.getColor(itemView.context, R.color.dum_gray03))
            }
        }
    }
}