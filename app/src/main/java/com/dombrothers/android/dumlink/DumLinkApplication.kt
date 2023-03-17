package com.dombrothers.android.dumlink

import android.app.Application
import timber.log.Timber

class DumLinkApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}