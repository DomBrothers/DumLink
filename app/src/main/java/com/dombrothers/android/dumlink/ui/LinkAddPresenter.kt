package com.dombrothers.android.dumlink.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.data.LinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LinkAddPresenter(val view: LinkAddContract.View) : ViewModel(), LinkAddContract.Presenter {
    private val linkRepository = LinkRepository()

    override fun getLink(title: String?) {
        title ?: return

        viewModelScope.launch(Dispatchers.IO) {
            try {
                linkRepository.handleSendText(title).apply {
                    setDataView(this)
                }
            } catch (e: Exception) {
                Timber.d("crawling error")
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