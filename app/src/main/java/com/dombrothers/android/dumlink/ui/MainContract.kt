package com.dombrothers.android.dumlink.ui

import com.dombrothers.android.dumlink.data.LinkResponse

class MainContract {
    interface View {
        fun setLinkList(linkResponse: LinkResponse)
    }

    interface Presenter {
        fun getAllLink()
    }
}