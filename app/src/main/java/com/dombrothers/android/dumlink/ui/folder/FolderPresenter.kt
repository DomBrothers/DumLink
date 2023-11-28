package com.dombrothers.android.dumlink.ui.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class FolderPresenter(val view: FolderContract.View) : ViewModel(), FolderContract.Presenter {
    private val folderRepository = FolderRepository()
    private val linkRepository = LinkRepository()
    override fun getFolderItem(id: Int) {
        folderRepository.getFolderItem(id).enqueue(object : Callback<LinkResponse> {
            override fun onResponse(call: Call<LinkResponse>, response: Response<LinkResponse>) {
                response.body()?.let { view.setFolderItem(it) }
                Timber.d(" getFolderItem success")
            }

            override fun onFailure(call: Call<LinkResponse>, t: Throwable) {
                Timber.d(" getFolderItem error")
            }
        })
    }

    override fun deleteLink(id: Int) {
        linkRepository.deleteLink(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.deleteLinkSuccess()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.deleteLinkSuccess()

            }
        })
    }

    override fun patchFolder(folderName: String, id: Int) {
        val body = FolderRequest(folderName)
        folderRepository.patchFolder(body, id).enqueue(object : Callback<FolderResponseItem> {
            override fun onResponse(call: Call<FolderResponseItem>, response: Response<FolderResponseItem>) {
                response.body()?.let { view.patchFolderSuccess(it.folderName) }
            }

            override fun onFailure(call: Call<FolderResponseItem>, t: Throwable) {

            }
        })
    }

    override fun deleteFolder(id: Int) {
        folderRepository.deleteFolder(id).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                view.deleteFolderSuccess()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.deleteFolderSuccess()
            }
        })
    }
}