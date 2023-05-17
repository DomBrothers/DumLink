package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Folder(
    val imageUrl: String,
    val folderName: String,
    val linkCount: Int,
    val links: ArrayList<Link>,
    var isChecked: Boolean = false
): Parcelable