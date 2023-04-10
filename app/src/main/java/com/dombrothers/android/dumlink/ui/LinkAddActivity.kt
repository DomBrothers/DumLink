package com.dombrothers.android.dumlink.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.ActivityLinkAddBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LinkAddActivity : BaseActivity<ActivityLinkAddBinding>(ActivityLinkAddBinding::inflate),
    LinkAddContract.View {
    private val linkAddPresenter by lazy { LinkAddPresenter(this) }
    private val folderAdapter by lazy { FolderAdapter(::folderListener) }

    private val testLinks1 = arrayListOf(
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", "")
    )

    private val testLinks2 = arrayListOf(
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", "")
    )

    private val testItems = arrayListOf(
        Folder("", "폴더1", 10, testLinks1), Folder("", "폴더2", 5, testLinks2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        val addType = intent.getStringExtra("add")

        if (addType != null) linkAddPresenter.getLink(addType)
        else linkAddPresenter.getLink(intent.getStringExtra(Intent.EXTRA_TEXT))
    }

    private fun initView() {
        with(binding) {
            linkAddRecyclerFolderList.adapter = folderAdapter
            folderAdapter.setItemList(testItems)
        }
    }

    private fun folderListener(folder: Folder) {
        val intent = Intent(this, FolderActivity::class.java)
        intent.putExtra("folder", folder)
        startActivity(intent)
    }

    override fun setLink(link: Link) {
        val image = link.imageUrl ?: intent.getStringExtra("ogImage") ?: ""
        val title = link.title ?: intent.getStringExtra("title") ?: ""
        val uri = link.link

        with(binding) {
            Glide.with(this@LinkAddActivity).load(image).transform(
                CenterCrop(), RoundedCorners(8)
            ).into(linkAddImgThumbnail)

            linkAddTxtTitle.text = title
            linkAddTxtLink.text = uri
        }
    }
}