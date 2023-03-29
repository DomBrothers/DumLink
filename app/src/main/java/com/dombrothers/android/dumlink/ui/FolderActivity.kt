package com.dombrothers.android.dumlink.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.databinding.ActivityFolderBinding
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType

class FolderActivity : BaseActivity<ActivityFolderBinding>(ActivityFolderBinding::inflate) {
    private val linkAdapter by lazy { LinkAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val folder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("folder", Folder::class.java)
        } else {
            intent.getParcelableExtra("folder")
        }

        folder?.let {
            binding.folderTxtTitle.text = it.folderName

            val layoutManager = GridLayoutManager(this@FolderActivity, 1)
            binding.folderRecyclerLinkList.layoutManager = layoutManager
            binding.folderRecyclerLinkList.adapter = linkAdapter
            linkAdapter.linkViewType = LinkViewType.TYPE01
            linkAdapter.setItemList(folder.links)

            binding.folderRadioBtn1.setOnClickListener { v ->
                onRadioButtonClicked(v)
            }

            binding.folderRadioBtn2.setOnClickListener { v ->
                onRadioButtonClicked(v)
            }
        }
        binding.folderImgBack.setOnClickListener {
            finish()
        }
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            when (view.getId()) {
                R.id.folder_radio_btn1 -> {
                    val layoutManager = GridLayoutManager(this, 1)
                    binding.folderRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE01

                    binding.folderRadioBtn2.isChecked = false
                }
                R.id.folder_radio_btn2 -> {
                    val layoutManager = GridLayoutManager(this, 2)
                    binding.folderRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE02

                    binding.folderRadioBtn1.isChecked = false
                }
            }
        }
    }

}