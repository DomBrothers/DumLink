package com.dombrothers.android.dumlink.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.data.TagResponse
import com.dombrothers.android.dumlink.databinding.LinkItemType02LayoutBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class LinkType02ViewHolder(
    private val binding: LinkItemType02LayoutBinding, private val listener: LinkItemSpinnerListener
) : RecyclerView.ViewHolder(binding.root) {
    private val tagAdapter by lazy { TagAdapter2() }

    fun bind(item: LinkResponseItem) {
        binding.linkItemType02TxtTags.text = "#${item.firstTag} #${item.secondTag} #${item.thirdTag}"

        binding.linkItemType02Spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> { // 링크 수정
                            listener.modifyLink(item)
                            binding.linkItemType02Spinner.setSelection(2, false)
                        }
                        1 -> { // 링크 삭제
                            listener.removeLink(item)
                            binding.linkItemType02Spinner.setSelection(2, false)
                        }

                        else -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        with(binding) {
            Glide.with(itemView.context).load(item.image).error("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmNxd9%2FbtsjcaQQ6Yt%2F1MAaZUmCsoUzyf7wkAxVbk%2Fimg.png")
                .placeholder(R.drawable.item_placeholder).transform(CenterCrop())
                .into(linkItemType02Img)

            var title = item.title

            if (title.isNullOrBlank()) title = "무제"
            linkItemType02TxtTitle.text = title

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