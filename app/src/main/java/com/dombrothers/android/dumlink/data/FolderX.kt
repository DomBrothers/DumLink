package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FolderX(
    val folderId: Int,
    val folderName: String,
    val userId: Int
): Parcelable