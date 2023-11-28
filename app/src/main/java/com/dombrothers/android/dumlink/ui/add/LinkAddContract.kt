package com.dombrothers.android.dumlink.ui.add

import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.LinkResponseItem

class LinkAddContract {
    interface View {

        fun finished()
        fun setLink(link: LinkResponseItem?)

        fun setFolderList(folderResponse: FolderResponse)

        fun showLoading()

        fun dismissLoading()

        fun postFolderSuccess()

    }

    interface Presenter {
        fun getLink(title: String?)

        fun postLink(title: String?, folderId: Int?)

        fun getAllFolder()

        fun postFolder(folderName: String)

    }
}