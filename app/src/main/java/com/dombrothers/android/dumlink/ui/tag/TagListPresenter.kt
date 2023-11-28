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

class TagListPresenter(val view: TagListContract.View) : ViewModel(), TagListContract.Presenter {
    private val tagRepository = TagRepository()
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
}