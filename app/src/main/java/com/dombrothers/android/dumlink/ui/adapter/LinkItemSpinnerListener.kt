package com.dombrothers.android.dumlink.ui.adapter

import com.dombrothers.android.dumlink.data.LinkResponseItem

interface LinkItemSpinnerListener {
    fun storeFolder(link: LinkResponseItem)

    fun modifyLink(link: LinkResponseItem)

    fun removeLink(link: LinkResponseItem)
}