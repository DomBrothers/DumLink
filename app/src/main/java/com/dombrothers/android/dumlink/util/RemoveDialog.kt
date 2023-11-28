package com.dombrothers.android.dumlink.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dombrothers.android.dumlink.databinding.DialogFolderCreateBinding
import com.dombrothers.android.dumlink.databinding.DialogFolderModifyBinding
import com.dombrothers.android.dumlink.databinding.DialogRemoveBinding

class RemoveDialog(private val title: String? = null, val listener: (Int) -> Unit, var id: Int? = null) : DialogFragment() {

    private lateinit var binding: DialogRemoveBinding

    override fun onResume() {
        super.onResume()

        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val params = dialog?.window?.attributes
            params?.apply {
                width = (resources.displayMetrics.widthPixels * 0.9).toInt()
                height = ViewGroup.LayoutParams.WRAP_CONTENT
            }

            attributes = params
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogRemoveBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        title?.let {
            binding.dialogRemoveTitle.text = it
        }

        binding.dialogRemoveBtnNo.setOnClickListener {
            dismiss()
        }

        binding.dialogRemoveBtnYes.setOnClickListener {
            id?.let { it1 -> listener(it1) }
            dismiss()
        }
    }
}