package com.dombrothers.android.dumlink.ui.modify

import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.TagResponse

class LinkModifyContract {
    interface View {
        fun patchLinkSuccess()
    }

    interface Presenter {
        fun patchLink(linkId: Int, folderId: Int)
    }
}