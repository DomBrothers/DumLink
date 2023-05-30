package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("/dum")
    fun getAllLink(): Call<LinkResponse>

    @GET("/dum/folder")
    fun getAllFolder(): Call<FolderResponse>

    @POST("/dum/input")
    fun postLink(@Body link: LinkRequest): Call<LinkAddResponse>
}