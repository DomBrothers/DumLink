package com.dombrothers.android.dumlink.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.ActivityFolderBinding
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkItemSpinnerListener
import com.dombrothers.android.dumlink.ui.adapter.LinkSpinnerAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.FolderModifyDialog
import com.dombrothers.android.dumlink.util.RemoveDialog

class FolderActivity : BaseActivity<ActivityFolderBinding>(ActivityFolderBinding::inflate),
    LinkItemSpinnerListener {
    private val linkAdapter by lazy { LinkAdapter(this) }
    private lateinit var folder: Folder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        folder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("folder", Folder::class.java)!!
        } else {
            intent.getParcelableExtra("folder")!!
        }


        binding.folderTxtTitle.text = folder.folderName

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
        binding.folderImgBack.setOnClickListener {
            finish()
        }

        binding.folderImgMore.setOnClickListener {
            binding.folderSpinner.performClick()
        }

        binding.folderSpinner.adapter = LinkSpinnerAdapter(
            this, resources.getStringArray(R.array.folder_setting).toList()
        )

        binding.folderSpinner.setSelection(2, false)

        binding.folderSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when (position) {
                    0 -> {
                        val dialog = FolderModifyDialog()
                        dialog.show(supportFragmentManager, null)
                        binding.folderSpinner.setSelection(2, false)
                    }

                    1 -> {
                        val dialog = RemoveDialog()
                        dialog.show(supportFragmentManager, null)
                        binding.folderSpinner.setSelection(2, false)
                    }

                    else -> {

                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
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

    override fun storeFolder(position: Int) {
        val dialog = FolderCreateDialog()
        dialog.show(supportFragmentManager, null)
    }

    override fun modifyLink(position: Int) {
        val intent = Intent(this, LinkModifyActivity::class.java)
        intent.putExtra("modify", folder!!.links[position])
        startActivity(intent)
    }

    override fun removeLink(position: Int) {
        val dialog = RemoveDialog("링크 수정")
        dialog.show(supportFragmentManager, null)
    }
}