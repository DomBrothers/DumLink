package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {
    @GET("/dum")
    fun getAllLink(): Call<LinkResponse>

    @GET("/dum/folder")
    fun getAllFolder(): Call<FolderResponse>

    @GET("/dum/folder/{id}")
    fun getFolderItem(@Path("id") id: Int): Call<LinkResponse>

    @POST("/dum/input")
    fun postLink(@Body link: LinkRequest): Call<LinkAddResponse>

    @GET("/dum/tags")
    fun getAllTag(): Call<TagResponse>

    @GET("/dum/tags/{id}")
    fun getTagItem(@Path("id") id: String): Call<LinkResponse>

    @POST("/dum/folder/add")
    fun postFolder(@Body folder: FolderRequest): Call<Folder>

    @PATCH("/dum/{id}")
    fun patchLink(@Body body: LinkModifyRequest, @Path("id") id: Int): Call<LinkResponseItem>

    @DELETE("/dum/{id}")
    fun deleteLink(@Path("id") id: Int): Call<String>

    @PATCH("/dum/folder/{id}")
    fun patchFolder(@Body body: FolderRequest, @Path("id") id: Int): Call<FolderResponseItem>

    @DELETE("/dum/folder/{id}")
    fun deleteFolder(@Path("id") id: Int): Call<String>
}