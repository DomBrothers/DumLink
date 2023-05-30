package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.create


class LinkRepository {
    private val linkScrap = LinkScrap()

    fun handleSendText(title: String): Pair<String, Map<String, String>> {
        return linkScrap.handleSendText(title)
    }

    fun postLink(link: LinkRequest): Call<LinkAddResponse> {
        return RetrofitClient.retrofit.create<RetrofitService>().postLink(link)
    }

    fun getAllLink(): Call<LinkResponse> {
        return  RetrofitClient.retrofit.create<RetrofitService>().getAllLink()
    }
}
