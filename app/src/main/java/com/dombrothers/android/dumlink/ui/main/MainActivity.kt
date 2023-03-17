package com.dombrothers.android.dumlink.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}