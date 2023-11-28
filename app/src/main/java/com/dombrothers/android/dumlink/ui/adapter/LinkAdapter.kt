package com.dombrothers.android.dumlink.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.LinkItemType01LayoutBinding
import com.dombrothers.android.dumlink.databinding.LinkItemType02LayoutBinding

class LinkAdapter(private val listener: LinkItemSpinnerListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var linkViewType: LinkViewType = LinkViewType.TYPE01
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val items = ArrayList<LinkResponseItem>()

    fun setItemList(newItems: List<LinkResponseItem>) {
        items.run {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (linkViewType) {
        LinkViewType.TYPE01 -> R.layout.link_item_type01_layout
        LinkViewType.TYPE02 -> R.layout.link_item_type02_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.link_item_type01_layout -> {
                LinkType01ViewHolder(
                    LinkItemType01LayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), listener
                )
            }
            else -> {
                LinkType02ViewHolder(
                    LinkItemType02LayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), listener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LinkType01ViewHolder -> {
                holder.bind(items[position])
            }
            is LinkType02ViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount() = items.size
}