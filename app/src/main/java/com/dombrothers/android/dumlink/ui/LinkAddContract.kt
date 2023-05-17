package com.dombrothers.android.dumlink.ui

import com.dombrothers.android.dumlink.data.Link

class LinkAddContract {
    interface View {
        fun setLink(link: Link?)

    }

    interface Presenter {
        fun getLink(title: String?)
    }
}