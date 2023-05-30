package com.dombrothers.android.dumlink.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.LinkItemType02LayoutBinding

class LinkType02ViewHolder(
    private val binding: LinkItemType02LayoutBinding, private val listener: LinkItemSpinnerListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.linkItemType02Spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> { // 링크 수정
                            listener.modifyLink(adapterPosition)
                            binding.linkItemType02Spinner.setSelection(2, false)
                        }
                        1 -> { // 링크 삭제
                            listener.removeLink(adapterPosition)
                            binding.linkItemType02Spinner.setSelection(2, false)
                        }

                        else -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    fun bind(item: LinkResponseItem) {
        with(binding) {
            Glide.with(itemView.context).load(item.image).error(R.drawable.item_placeholder)
                .placeholder(R.drawable.item_placeholder).transform(CenterCrop())
                .into(linkItemType02Img)

            linkItemType02TxtTitle.text = item.title

            linkItemType02TxtLink.text = item.link

            linkItemType02ImgMore.setOnClickListener {
                linkItemType02Spinner.performClick()
            }

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                itemView.context.startActivity(intent)
            }

            linkItemType02Spinner.adapter = LinkSpinnerAdapter(
                itemView.context, itemView.resources.getStringArray(R.array.rink_setting).toList()
            )
            linkItemType02Spinner.setSelection(2, false)
        }
    }
}