package com.dombrothers.android.dumlink.data

import retrofit2.Call
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Path

class FolderRepository {
    fun getAllFolder(): Call<FolderResponse>{
        return RetrofitClient.retrofit.create<RetrofitService>().getAllFolder()
    }

    fun getFolderItem(id: Int): Call<LinkResponse>{
        return RetrofitClient.retrofit.create<RetrofitService>().getFolderItem(id)
    }

    fun postFolder(folder: FolderRequest): Call<Folder> {
        return RetrofitClient.retrofit.create<RetrofitService>().postFolder(folder)
    }

    fun patchFolder(body: FolderRequest, id: Int): Call<FolderResponseItem> {
        return RetrofitClient.retrofit.create<RetrofitService>().patchFolder(body, id)
    }


    fun deleteFolder(id: Int): Call<String> {
        return RetrofitClient.retrofit.create<RetrofitService>().deleteFolder(id)
    }

}