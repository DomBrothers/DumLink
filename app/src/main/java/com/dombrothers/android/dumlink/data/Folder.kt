package com.dombrothers.android.dumlink.data

data class Folder(val imageUrl: String, val folderName: String, val linkCount: Int, var isSelected: Boolean = false, val isMainFolder: Boolean = false)