package com.dombrothers.android.dumlink.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter, ViewModel() {
    private val linkRepository = LinkRepository()
    private val folderRepository = FolderRepository()
    private val tagRepository = TagRepository()
    override fun getAllLink() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                linkRepository.getAllLink().enqueue(object : Callback<LinkResponse> {
                    override fun onResponse(
                        call: Call<LinkResponse>, response: Response<LinkResponse>
                    ) {
                        response.body()?.let { view.setLinkList(it) }
                        Timber.d("getAllLink success")
                    }

                    override fun onFailure(call: Call<LinkResponse>, t: Throwable) {
                        Timber.d("getAllLink error")
                    }

                })
            } catch (e: Exception) {
                Timber.d("getAllLink error")
            }
        }
    }

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

    override fun getAllTag() {
        try {
            tagRepository.getAllTag().enqueue(object : Callback<TagResponse> {
                override fun onResponse(
                    call: Call<TagResponse>, response: Response<TagResponse>
                ) {
                    response.body()?.let { view.setTagList(it) }
                    Timber.d("getAllTag success")
                }

                override fun onFailure(call: Call<TagResponse>, t: Throwable) {
                    Timber.d("getAllTag error")
                }
            })

        } catch (e: Exception) {
            Timber.d("getAllTag error")
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
}