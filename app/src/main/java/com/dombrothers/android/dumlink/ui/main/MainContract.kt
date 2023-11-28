package com.dombrothers.android.dumlink.ui.main

import com.dombrothers.android.dumlink.data.FolderRequest
import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.TagResponse

class MainContract {
    interface View {
        fun setLinkList(linkResponse: LinkResponse)

        fun setFolderList(folderResponse: FolderResponse)

        fun setTagList(tagResponse: TagResponse)

        fun postFolderSuccess()

        fun deleteLinkSuccess()

    }

    interface Presenter {
        fun getAllLink()

        fun getAllFolder()

        fun getAllTag()

        fun postFolder(folderName: String)

        fun deleteLink(id: Int)
    }
}

