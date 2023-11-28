package com.dombrothers.android.dumlink.ui.tag

import com.dombrothers.android.dumlink.data.LinkRepository
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.LinkResponseItem

class TagContract {
    interface View {
        fun deleteLinkSuccess()
        fun setTagItem(linkResponse: LinkResponse)
    }

    interface Presenter {
        fun getTagItem(id: String)
        fun deleteLink(id: Int)
    }
}