package com.dombrothers.android.dumlink.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(val imageUrl: String?, val title: String?, val link: String?, val folder: String? = null) :
    Parcelable
