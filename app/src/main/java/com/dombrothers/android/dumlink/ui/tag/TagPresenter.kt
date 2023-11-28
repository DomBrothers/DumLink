package com.dombrothers.android.dumlink.ui.tag

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

class TagPresenter(val view: TagContract.View) : ViewModel(), TagContract.Presenter {
    private val tagRepository = TagRepository()
    private val linkRepository = LinkRepository()

    override fun getTagItem(id: String) {
        tagRepository.getTagItem(id).enqueue(object : Callback<LinkResponse> {
            override fun onResponse(call: Call<LinkResponse>, response: Response<LinkResponse>) {
                response.body()?.let { view.setTagItem(it) }
                Timber.d("getTagItem success")
            }

            override fun onFailure(call: Call<LinkResponse>, t: Throwable) {
                Timber.d("getTagItem error")
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

