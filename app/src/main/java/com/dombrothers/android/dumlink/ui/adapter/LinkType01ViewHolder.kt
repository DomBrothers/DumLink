package com.dombrothers.android.dumlink.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding

class LinkType01ViewHolder(
    private val binding: LinkItemType01LayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Link) {
        with(binding) {
            linkItemType01TxtTitle.text = item.title

            linkItemType01TxtLink.text = item.link
        }
    }
}