package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkResponseItem(
    val description: String? = null,
    val firstTag: String? = null,
    val folder: Folder? = null,
    val id: Int? = null,
    val image: String? = null,
    val link: String? = null,
    val secondTag: String? = null,
    val thirdTag: String? = null,
    val title: String? = null,
    val userId: Int? = null
): Parcelable