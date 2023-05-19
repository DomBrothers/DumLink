package com.dombrothers.android.dumlink.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.ActivityLinkAddBinding
import com.dombrothers.android.dumlink.databinding.ActivityLinkModifyBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderAdapter
import com.dombrothers.android.dumlink.ui.adapter.FolderChoiceAdapter
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.Util.setStatusBarColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LinkModifyActivity :
    BaseActivity<ActivityLinkModifyBinding>(ActivityLinkModifyBinding::inflate),
    LinkAddContract.View {
    private val folderAdapter by lazy { FolderChoiceAdapter(::folderListener) }

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
        Folder("", "공부할 것", 10, testLinks1), Folder("", "과제 참고 링크", 5, testLinks2), Folder("", "프로그래밍", 5, testLinks2),
        Folder("", "취업정보", 5, testLinks2), Folder("", "코딩테스트", 5, testLinks2), Folder("", "알고리즘", 5, testLinks2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(getColor(R.color.transparent))
        initView()
        val link = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("modify", Link::class.java)
        } else {
            intent.getParcelableExtra("modify")
        }
        setLink(link)
    }

    private fun initView() {
        with(binding) {
            linkModifyLlContainer.setOnClickListener {
                hideKeyboard()
                linkModifyEditTxtTitle.clearFocus()
            }
            linkModifyRecyclerFolderList.adapter = folderAdapter
            folderAdapter.setItemList(testItems)
            linkModifyLlFolderAdd.setOnClickListener {
                val dialog = FolderCreateDialog()
                dialog.show(supportFragmentManager, null)
            }
        }
    }

    private fun folderListener(position: Int) {
    }

    override fun setLink(link: Link?) {
        link ?: return

        val image = link.imageUrl
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
}