package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Folder(
    val folderId: Int,
    val folderName: String,
    val userId: Int = 0,
    var isChecked: Boolean = false
): Parcelable