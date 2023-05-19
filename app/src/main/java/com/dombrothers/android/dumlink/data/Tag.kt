package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tag(val tagName: String, val linkList: List<Link>) : Parcelable
