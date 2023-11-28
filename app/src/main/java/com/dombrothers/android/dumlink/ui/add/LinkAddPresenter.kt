package com.dombrothers.android.dumlink.ui.add

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

class LinkAddPresenter(val view: LinkAddContract.View) : ViewModel(), LinkAddContract.Presenter {
    private val linkRepository = LinkRepository()
    private val folderRepository = FolderRepository()

    override fun getAllFolder() {
        try {
            folderRepository.getAllFolder().enqueue(object : Callback<FolderResponse> {
                override fun onResponse(
                    call: Call<FolderResponse>, response: Response<FolderResponse>
                ) {
                    response.body()?.let { view.setFolderList(it) }
                    Timber.d("getAllFolder success")
                }

                override fun onFailure(call: Call<FolderResponse>, t: Throwable) {
                    Timber.d("getAllFolder error")
                }
            })

        } catch (e: Exception) {
            Timber.d("getAllFolder error")
        }
    }

    override fun postLink(title: String?, folderId: Int?) {
        title ?: return
        view.showLoading()
        linkRepository.postLink(LinkRequest(title, folderId)).enqueue(object :
            Callback<LinkAddResponse> {
            override fun onResponse(
                call: Call<LinkAddResponse>,
                response: Response<LinkAddResponse>
            ) {
                Timber.d("postLink success")
                view.dismissLoading()
                view.finished()
            }

            override fun onFailure(call: Call<LinkAddResponse>, t: Throwable) {
                Timber.d("postLink error")
                view.dismissLoading()
                view.finished()
            }
        })
    }

    override fun getLink(title: String?) {
        title ?: return
        view.showLoading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                linkRepository.handleSendText(title).apply {
                    setDataView(this)
                }
            } catch (e: Exception) {
                Timber.d("crawling error")
            } finally {
                view.dismissLoading()
            }
        }
    }

    private suspend fun setDataView(result: Pair<String, Map<String, String>>) {
        val url = result.first
        val ogMap = result.second

        val image: String? = ogMap["image"]
        val title: String? = ogMap["title"]

        val link = LinkResponseItem(image = image, title = title, link = url)

        withContext(Dispatchers.Main) {
            view.setLink(link)
        }
    }

    override fun postFolder(folderName: String) {
        val folderRequest = FolderRequest(folderName)

        folderRepository.postFolder(folderRequest).enqueue(object : Callback<Folder> {
            override fun onResponse(call: Call<Folder>, response: Response<Folder>) {
                view.postFolderSuccess()
            }

            override fun onFailure(call: Call<Folder>, t: Throwable) {

            }
        })
    }

}