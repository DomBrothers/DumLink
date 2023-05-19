package com.dombrothers.android.dumlink.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding
import timber.log.Timber

class LinkType01ViewHolder(
    private val binding: LinkItemType01LayoutBinding, private val listener: LinkItemSpinnerListener
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.linkItemType01Spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> { // 링크 수정
                            listener.modifyLink(adapterPosition)
                            binding.linkItemType01Spinner.setSelection(2, false)
                        }
                        1 -> { // 링크 삭제
                            listener.removeLink(adapterPosition)
                            binding.linkItemType01Spinner.setSelection(2, false)
                        }

                        else -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }
    fun bind(item: Link) {
        with(binding) {
            linkItemType01TxtTitle.text = item.title

            linkItemType01TxtLink.text = item.link

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