package com.dombrothers.android.dumlink.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.LinkRepository
import com.dombrothers.android.dumlink.data.LinkRequest
import com.dombrothers.android.dumlink.data.LinkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter, ViewModel() {
    private val linkRepository = LinkRepository()
    override fun getAllLink() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                linkRepository.getAllLink().enqueue(object : Callback<LinkResponse> {
                    override fun onResponse(
                        call: Call<LinkResponse>,
                        response: Response<LinkResponse>
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
}