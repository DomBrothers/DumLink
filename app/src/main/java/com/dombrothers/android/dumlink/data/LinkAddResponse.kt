package com.dombrothers.android.dumlink.data

data class LinkAddResponse(
    val description: String,
    val firstTag: String,
    val folderId: Int,
    val id: Int,
    val image: String,
    val link: String,
    val secondTag: String,
    val thirdTag: String,
    val title: String,
    val userId: Any
)