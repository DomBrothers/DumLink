package com.dombrothers.android.dumlink.ui.add

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.ActivityLinkAddBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderChoiceAdapter
import com.dombrothers.android.dumlink.ui.main.MainActivity
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.Util.setStatusBarColor

class LinkAddActivity : BaseActivity<ActivityLinkAddBinding>(ActivityLinkAddBinding::inflate),
    LinkAddContract.View {
    private val presenter = LinkAddPresenter(this)
    private var folderId: Int = 1
    private var url: String? = null
    private val linkAddPresenter by lazy { LinkAddPresenter(this) }
    private val folderAdapter by lazy { FolderChoiceAdapter(::folderListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(getColor(R.color.transparent))
        initView()

        presenter.getAllFolder()

        val addType = intent.getStringExtra("add")
        if (addType != null) linkAddPresenter.getLink(addType)
        else linkAddPresenter.getLink(intent.getStringExtra(Intent.EXTRA_TEXT))
    }

    private fun initView() {
        with(binding) {
            linkAddRecyclerFolderList.adapter = folderAdapter
            binding.linkAddBtnStore.setOnClickListener {
                linkAddPresenter.postLink(url, folderId)

            }
            linkAddLlFolderAdd.setOnClickListener {
                val dialog = FolderCreateDialog(::createFolder)
                dialog.show(supportFragmentManager, null)
            }
        }
    }

    private fun createFolder(folderName: String) {
        showLoadingDialog(this)
        presenter.postFolder(folderName)
    }

    private fun folderListener(folder: FolderResponseItem) {
        folderId = folder.folderId
        folderAdapter.choiceFolder(folderId)
    }

    override fun setFolderList(folderResponse: FolderResponse) {
        folderAdapter.setItemList(folderResponse)
    }

    override fun finished() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun showLoading() {
        showLoadingDialog(this)
    }

    override fun dismissLoading() {
        dismissLoadingDialog()
    }

    override fun setLink(link: LinkResponseItem?) {
        link ?: return
        val image = link.image ?: intent.getStringExtra("ogImage") ?: ""
        val title = link.title ?: intent.getStringExtra("title") ?: ""
        val uri = link.link

        with(binding) {
            Glide.with(this@LinkAddActivity).load(image)
                .error("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmNxd9%2FbtsjcaQQ6Yt%2F1MAaZUmCsoUzyf7wkAxVbk%2Fimg.png")
                .transform(
                    CenterCrop(), RoundedCorners(8)
                ).into(linkAddImgThumbnail)

            linkAddTxtTitle.text = title
            linkAddTxtLink.text = uri
            url = uri
        }
    }

    override fun postFolderSuccess() {
        dismissLoadingDialog()
        presenter.getAllFolder()
    }
}