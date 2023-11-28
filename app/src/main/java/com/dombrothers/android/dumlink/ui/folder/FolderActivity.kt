package com.dombrothers.android.dumlink.ui.folder

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.ActivityFolderBinding
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkItemSpinnerListener
import com.dombrothers.android.dumlink.ui.adapter.LinkSpinnerAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType
import com.dombrothers.android.dumlink.ui.modify.LinkModifyActivity
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.FolderModifyDialog
import com.dombrothers.android.dumlink.util.RemoveDialog

class FolderActivity : BaseActivity<ActivityFolderBinding>(ActivityFolderBinding::inflate),
    LinkItemSpinnerListener, FolderContract.View {
    private val folderPresenter = FolderPresenter(this)
    private val linkAdapter by lazy { LinkAdapter(this) }
    private lateinit var folder: FolderResponseItem

    val presenter = FolderPresenter(this)

    override fun deleteLinkSuccess() {
        presenter.getFolderItem(folder.folderId)
    }

    override fun deleteFolderSuccess() {
        finish()
    }

    override fun patchFolderSuccess(folderName: String) {
        binding.folderTxtTitle.text = folderName
    }

    fun deleteLink(id: Int) {
        presenter.deleteLink(id)
    }

    fun deleteFolder(id: Int) {
        presenter.deleteFolder(id)
    }

    fun patchFolder(folderName: String) {
        presenter.patchFolder(folderName, folder.folderId)
    }
    override fun storeFolder(link: LinkResponseItem) {
        TODO("Not yet implemented")
    }

    override fun modifyLink(link: LinkResponseItem) {
        val intent = Intent(this, LinkModifyActivity::class.java)
        intent.putExtra("modify", link)
        startActivity(intent)
    }

    override fun removeLink(link: LinkResponseItem) {
        val dialog = RemoveDialog("링크 삭제", ::deleteLink, link.id!!)
        dialog.show(supportFragmentManager, null)
    }

    override fun onResume() {
        super.onResume()
        folderPresenter.getFolderItem(folder.folderId)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        folder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("folder", FolderResponseItem::class.java)!!
        } else {
            intent.getParcelableExtra("folder")!!
        }



        binding.folderTxtTitle.text = folder.folderName

        val layoutManager = GridLayoutManager(this@FolderActivity, 2)
        binding.folderRecyclerLinkList.layoutManager = layoutManager
        binding.folderRecyclerLinkList.adapter = linkAdapter
        linkAdapter.linkViewType = LinkViewType.TYPE02


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
                        val dialog = FolderModifyDialog(::patchFolder)
                        dialog.show(supportFragmentManager, null)

                        binding.folderSpinner.setSelection(2, false)
                    }

                    1 -> {
                        val dialog = RemoveDialog("폴더 삭제", ::deleteFolder, folder.folderId)
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



    override fun showLoading() {
        showLoadingDialog(this)
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    override fun setFolderItem(linkResponse: LinkResponse) {
        linkAdapter.setItemList(linkResponse)
    }
}