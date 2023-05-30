package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkResponseItem(
    val description: String,
    val firstTag: String,
    val folder: FolderX,
    val id: Int,
    val image: String,
    val link: String,
    val secondTag: String,
    val thirdTag: String,
    val title: String,
    val userId: Int
): Parcelable