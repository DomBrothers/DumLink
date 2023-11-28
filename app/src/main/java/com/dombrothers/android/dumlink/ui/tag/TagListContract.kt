package com.dombrothers.android.dumlink.ui.tag

import com.dombrothers.android.dumlink.data.LinkRepository
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.TagResponse

class TagListContract {
    interface View {
        fun setTagList(tagResponse: TagResponse)
    }

    interface Presenter {
        fun getAllTag()
    }
}