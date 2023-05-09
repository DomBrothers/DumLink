package com.dombrothers.android.dumlink.ui.adapter

import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding

class LinkType01ViewHolder(
    private val binding: LinkItemType01LayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Link) {
        with(binding) {
            linkItemType01TxtTitle.text = item.title

            linkItemType01TxtLink.text = item.link

            linkItemType01ImgMore.setOnClickListener {
                linkItemType01Spinner.performClick()
            }

            ArrayAdapter.createFromResource(
                itemView.context,
                R.array.rink_setting,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                linkItemType01Spinner.adapter = adapter
            }
        }
    }
}