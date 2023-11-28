package com.dombrothers.android.dumlink.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.databinding.ActivityLoginBinding
import com.dombrothers.android.dumlink.ui.main.MainActivity
import com.dombrothers.android.dumlink.util.Util
import com.dombrothers.android.dumlink.util.Util.setStatusBarColor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    private lateinit var googleSignResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Util.setFullScreenWithStatusBar(this)
        setStatusBarColor(getColor(R.color.transparent))

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)


        googleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }

        binding.loginImgBtnGoogle.setOnClickListener {
            val signIntent: Intent = mGoogleSignInClient.signInIntent
            googleSignResultLauncher.launch(signIntent)
        }


    }

    private fun updateUI(account: GoogleSignInAccount?) {
        account ?: return
        Util.startActivityWithSmoothAnimation(this, MainActivity::class.java)
        finish()
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val token = account?.idToken.toString()
            val tokenAuth = account?.serverAuthCode.toString()

            Timber.tag("Google account").d(email)
            Timber.tag("Google account").d(token)
            Timber.tag("Google account").d(tokenAuth)

            updateUI(account)
        } catch (e: ApiException) {
            Timber.tag("Google account").e("signInResult:failed Code = %s", e.statusCode)
        }
    }
}