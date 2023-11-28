package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path


class TagRepository {

    fun getAllTag(): Call<TagResponse>{
        return RetrofitClient.retrofit.create<RetrofitService>().getAllTag()
    }

    fun getTagItem(id: String): Call<LinkResponse> {
        return RetrofitClient.retrofit.create<RetrofitService>().getTagItem(id)
    }

}
