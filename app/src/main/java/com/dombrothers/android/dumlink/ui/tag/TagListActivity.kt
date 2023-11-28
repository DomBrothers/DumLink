package com.dombrothers.android.dumlink.ui.tag

import android.content.Intent
import android.os.Bundle
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.TagResponse
import com.dombrothers.android.dumlink.databinding.ActivityTagListBinding
import com.dombrothers.android.dumlink.ui.adapter.*
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class TagListActivity : BaseActivity<ActivityTagListBinding>(ActivityTagListBinding::inflate), TagListContract.View {
    override fun setTagList(tagResponse: TagResponse) {
        tagAdapter.setItemList(tagResponse)
    }

    private val presenter = TagListPresenter(this)

    private val tagAdapter by lazy { TagAdapter(::tagListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getAllTag()
        with(binding) {
            val tagLayoutManager = FlexboxLayoutManager(this@TagListActivity)
            tagLayoutManager.flexDirection = FlexDirection.ROW
            tagListRecyclerLinkList.layoutManager = tagLayoutManager
            tagListRecyclerLinkList.adapter = tagAdapter

            tagListImgBack.setOnClickListener {
                finish()
            }
        }
    }


    private fun tagListener(tag: String) {
        val intent = Intent(this, TagActivity::class.java)
        intent.putExtra("tag", tag)
        startActivity(intent)
    }



}