package com.dombrothers.android.dumlink.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.data.LinkAddResponse
import com.dombrothers.android.dumlink.data.LinkRepository
import com.dombrothers.android.dumlink.data.LinkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LinkAddPresenter(val view: LinkAddContract.View) : ViewModel(), LinkAddContract.Presenter {
    private val linkRepository = LinkRepository()

    override fun postLink(title: String?) {
        title ?: return
        view.showLoading()
        linkRepository.postLink(LinkRequest(title)).enqueue(object :
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

        val link = Link(image, title, url)

        withContext(Dispatchers.Main) {
            view.setLink(link)
        }
    }


}