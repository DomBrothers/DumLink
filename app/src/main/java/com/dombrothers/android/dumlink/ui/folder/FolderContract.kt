package com.dombrothers.android.dumlink.ui.folder

import com.dombrothers.android.dumlink.data.FolderRequest
import com.dombrothers.android.dumlink.data.LinkRepository
import com.dombrothers.android.dumlink.data.LinkResponse

class FolderContract {
    interface View {
        fun showLoading()
        fun dismissLoading()
        fun setFolderItem(linkResponse: LinkResponse)
        fun deleteLinkSuccess()
        fun deleteFolderSuccess()
        fun patchFolderSuccess(folderName: String)

    }

    interface Presenter {
        fun getFolderItem(id: Int)
        fun deleteLink(id: Int)
        fun patchFolder(folderName: String, id: Int)
        fun deleteFolder(id: Int)
    }
}