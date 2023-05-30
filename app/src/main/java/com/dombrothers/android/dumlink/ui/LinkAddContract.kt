package com.dombrothers.android.dumlink.ui

import com.dombrothers.android.dumlink.data.Link

class LinkAddContract {
    interface View {

        fun finished()
        fun setLink(link: Link?)

        fun showLoading()

        fun dismissLoading()

    }

    interface Presenter {
        fun getLink(title: String?)

        fun postLink(title: String?)
    }
}