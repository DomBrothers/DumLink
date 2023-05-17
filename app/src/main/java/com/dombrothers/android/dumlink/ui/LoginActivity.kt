package com.dombrothers.android.dumlink.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.databinding.ActivityLoginBinding
import com.dombrothers.android.dumlink.util.Util
import com.dombrothers.android.dumlink.util.Util.setStatusBarColor

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.setFullScreenWithStatusBar(this)
        setStatusBarColor(getColor(R.color.transparent))
        binding.loginImgBtnGoogle.setOnClickListener {
            Util.startActivityWithSmoothAnimation(this, MainActivity::class.java)
        }


    }
}