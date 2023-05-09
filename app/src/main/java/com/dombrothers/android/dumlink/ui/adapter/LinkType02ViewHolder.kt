package com.dombrothers.android.dumlink.ui.adapter

import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.LinkItemType02LayoutBinding

class LinkType02ViewHolder(
    private val binding: LinkItemType02LayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Link) {
        with(binding) {
            Glide.with(itemView.context).load(item.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background).transform(CenterCrop())
                .into(linkItemType02Img)

            linkItemType02TxtTitle.text = item.title

            linkItemType02TxtLink.text = item.link

            linkItemType02ImgMore.setOnClickListener {
                linkItemType02Spinner.performClick()
            }

            ArrayAdapter.createFromResource(
                itemView.context,
                R.array.rink_setting,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                linkItemType02Spinner.adapter = adapter
            }
        }
    }
}