package com.dombrothers.android.dumlink.ui.modify

import android.os.Build
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.ActivityLinkModifyBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderChoiceAdapter
import com.dombrothers.android.dumlink.ui.add.LinkAddContract
import com.dombrothers.android.dumlink.ui.add.LinkAddPresenter
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.Util.setStatusBarColor

class LinkModifyActivity :
    BaseActivity<ActivityLinkModifyBinding>(ActivityLinkModifyBinding::inflate),
    LinkAddContract.View, LinkModifyContract.View {

    var folderId: Int? = null
    var linkId: Int? = null
    private val folderAdapter by lazy { FolderChoiceAdapter(::folderListener) }
    override fun patchLinkSuccess() {
        finish()
    }

    private val presenter = LinkAddPresenter(this)
    private val presenter2 = LinkModifyPresenter(this)

    override fun setFolderList(folderResponse: FolderResponse) {
        folderAdapter.setItemList(folderResponse)
        folderAdapter.choiceFolder(folderId!!)
    }

    override fun finished() {

    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(getColor(R.color.transparent))
        initView()

        presenter.getAllFolder()
        val link = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("modify", LinkResponseItem::class.java)
        } else {
            intent.getParcelableExtra("modify")
        }

        linkId = link!!.id

        folderId = link!!.folder!!.folderId
        setLink(link)
    }

    private fun initView() {
        with(binding) {
            linkModifyLlContainer.setOnClickListener {
                hideKeyboard()
                linkModifyEditTxtTitle.clearFocus()
            }

            binding.linkModifyBtnStore.setOnClickListener {
                presenter2.patchLink(linkId!!, folderId!!)
            }
            linkModifyRecyclerFolderList.adapter = folderAdapter
            linkModifyLlFolderAdd.setOnClickListener {
                val dialog = FolderCreateDialog(::createFolder)
                dialog.show(supportFragmentManager, null)
            }
        }
    }
    fun createFolder(folderName: String) {
        showLoadingDialog(this)
        presenter.postFolder(folderName)
    }

    private fun folderListener(folder: FolderResponseItem) {
        folderId = folder.folderId
        folderAdapter.choiceFolder(folderId!!)
    }

    override fun setLink(link: LinkResponseItem?) {
        link ?: return

        val image = link.image
        val title = link.title
        val uri = link.link

        with(binding) {
            Glide.with(this@LinkModifyActivity).load(image).transform(
                CenterCrop(), RoundedCorners(8)
            ).into(linkModifyImgThumbnail)

            linkModifyEditTxtTitle.setText(title)
            linkModifyTxtLink.text = uri
        }
    }
    override fun postFolderSuccess() {
        dismissLoadingDialog()
        presenter.getAllFolder()
    }
}