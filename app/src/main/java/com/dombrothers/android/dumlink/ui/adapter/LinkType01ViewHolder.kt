package com.dombrothers.android.dumlink.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.data.TagResponse
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding
import com.dombrothers.android.dumlink.ui.tag.TagActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class LinkType01ViewHolder(
    private val binding: LinkItemType01LayoutBinding, private val listener: LinkItemSpinnerListener
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: LinkResponseItem) {
        binding.linkItemType01TxtTags.text = "#${item.firstTag} #${item.secondTag} #${item.thirdTag}"

        binding.linkItemType01Spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> { // 링크 수정
                            listener.modifyLink(item)
                            binding.linkItemType01Spinner.setSelection(2, false)
                        }
                        1 -> { // 링크 삭제
                            listener.removeLink(item)
                            binding.linkItemType01Spinner.setSelection(2, false)
                        }

                        else -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        with(binding) {
            var title = item.title

            if (title.isNullOrBlank()) title = "무제"
            linkItemType01TxtTitle.text = title

            //linkItemType01TxtLink.text = item.link

            linkItemType01ImgMore.setOnClickListener {
                linkItemType01Spinner.performClick()
            }

            linkItemType01Spinner.adapter = LinkSpinnerAdapter(
                itemView.context, itemView.resources.getStringArray(R.array.rink_setting).toList()
            )

            linkItemType01Spinner.setSelection(2, false)

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                itemView.context.startActivity(intent)
            }
        }
    }
}