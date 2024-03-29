package com.dombrothers.android.dumlink.base

import android.content.Context
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.dombrothers.android.dumlink.util.LoadingDialog
import com.google.android.material.snackbar.Snackbar



abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B
    private val toastList = arrayListOf<Toast>() //출력할 Toast 객체를 저장할 리스트
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStop() {
        for (toast in toastList) {
            toast.cancel()
        }
        toastList.clear()
        super.onStop()
    }

    fun showCustomToast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
        toastList.add(toast)
    }

    fun showLoadingDialog(context: Context) {
        loadingDialog = LoadingDialog(context)
        loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    fun showCustomSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = window.currentFocus
        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}