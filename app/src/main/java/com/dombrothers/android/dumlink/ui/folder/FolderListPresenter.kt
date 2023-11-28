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

class FolderListPresenter(val view: FolderListContract.View) : ViewModel(), FolderListContract.Presenter {
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
}