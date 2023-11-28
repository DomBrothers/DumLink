package com.dombrothers.android.dumlink.ui.folder

import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.LinkResponse

class FolderListContract {
    interface View {
        fun showLoading()
        fun dismissLoading()

        fun setFolderList(folderResponse: FolderResponse)
    }

    interface Presenter {
        fun getAllFolder()
    }
}