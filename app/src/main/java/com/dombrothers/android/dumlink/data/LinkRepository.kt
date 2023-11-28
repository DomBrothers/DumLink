package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.Path


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

    fun patchLink(body: LinkModifyRequest, id: Int): Call<LinkResponseItem> {
        return RetrofitClient.retrofit.create<RetrofitService>().patchLink(body, id)
    }

    fun deleteLink(id: Int): Call<String> {
        return  RetrofitClient.retrofit.create<RetrofitService>().deleteLink(id)
    }
}
