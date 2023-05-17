package com.dombrothers.android.dumlink.ui.adapter

import com.dombrothers.android.dumlink.data.Link

interface LinkItemSpinnerListener {
    fun storeFolder(position: Int)

    fun modifyLink(position: Int)

    fun removeLink(position: Int)
}