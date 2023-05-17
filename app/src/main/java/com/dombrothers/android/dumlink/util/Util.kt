package com.dombrothers.android.dumlink.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.dombrothers.android.dumlink.R
import java.util.regex.Matcher
import java.util.regex.Pattern

object Util {
    fun <T : AppCompatActivity> startActivityWithSmoothAnimation(
        activity: AppCompatActivity, destination: Class<T>
    ) {
        activity.apply {
            startActivity(Intent(activity, destination))
            overridePendingTransition(R.anim.view_fade_in, R.anim.view_fade_out)
        }
    }
    @JvmStatic
    @SuppressLint("ObsoleteSdkInt")
    fun setFullScreenWithStatusBar(activity: AppCompatActivity) {
        activity.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }
    fun hideKeyboard(activity: Activity) {
        if (activity.currentFocus != null) {
            val inputManager: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    fun extractUrl(content: String): String {
        return try {
            val regex =
                "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
            val p: Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            val m: Matcher = p.matcher(content)
            if (m.find()) m.group()
            else ""
        } catch (e: Exception) {
            ""
        }
    }

    @Suppress("DEPRECATION")
    fun Activity.setStatusBarColor(color:Int){
        var flags = window?.decorView?.systemUiVisibility // get current flag
        if (flags != null) {
            if(isColorDark(color)){
                flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                window?.decorView?.systemUiVisibility = flags
            }else{
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window?.decorView?.systemUiVisibility = flags
            }
        }
        window?.statusBarColor = color
    }

    private fun isColorDark(color:Int) : Boolean{
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }
}