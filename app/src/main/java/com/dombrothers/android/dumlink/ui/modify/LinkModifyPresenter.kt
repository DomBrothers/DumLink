package com.dombrothers.android.dumlink.ui.modify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LinkModifyPresenter(private val view: LinkModifyContract.View) : LinkModifyContract.Presenter, ViewModel() {
    private val linkRepository = LinkRepository()
    private val folderRepository = FolderRepository()

    override fun patchLink(linkId: Int, folderId: Int) {
        val body = LinkModifyRequest(folderId)
        linkRepository.patchLink(body = body, id = linkId).enqueue(object : Callback<LinkResponseItem> {
            override fun onResponse(
                call: Call<LinkResponseItem>,
                response: Response<LinkResponseItem>
            ) {
                view.patchLinkSuccess()
            }

            override fun onFailure(call: Call<LinkResponseItem>, t: Throwable) {

            }
        })
    }
}