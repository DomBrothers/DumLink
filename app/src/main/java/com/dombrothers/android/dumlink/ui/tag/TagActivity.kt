package com.dombrothers.android.dumlink.ui.tag

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
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.data.LinkResponseItem
import com.dombrothers.android.dumlink.databinding.ActivityTagBinding
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkItemSpinnerListener
import com.dombrothers.android.dumlink.ui.adapter.LinkSpinnerAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType
import com.dombrothers.android.dumlink.ui.modify.LinkModifyActivity
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.RemoveDialog
import com.dombrothers.android.dumlink.util.TagModifyDialog

class TagActivity : BaseActivity<ActivityTagBinding>(ActivityTagBinding::inflate),
    LinkItemSpinnerListener, TagContract.View  {
    private val presenter = TagPresenter(this)
    override fun setTagItem(linkResponse: LinkResponse) {
        linkAdapter.setItemList(linkResponse)
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

    private val linkAdapter by lazy { LinkAdapter(this) }
    private lateinit var tag: String

    override fun deleteLinkSuccess() {
        presenter.getTagItem(tag)
    }

    fun deleteLink(id: Int) {
        presenter.deleteLink(id)
    }

    override fun onResume() {
        super.onResume()
        presenter.getTagItem(tag)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tag = intent.getStringExtra("tag").toString()

        binding.tagTxtTitle.text = tag

        val layoutManager = GridLayoutManager(this@TagActivity, 2)
        binding.tagRecyclerLinkList.layoutManager = layoutManager
        binding.tagRecyclerLinkList.adapter = linkAdapter
        linkAdapter.linkViewType = LinkViewType.TYPE02


        binding.tagRadioBtn1.setOnClickListener { v ->
            onRadioButtonClicked(v)
        }

        binding.tagRadioBtn2.setOnClickListener { v ->
            onRadioButtonClicked(v)
        }
        binding.tagImgBack.setOnClickListener {
            finish()
        }

        binding.tagImgMore.setOnClickListener {
            binding.tagSpinner.performClick()
        }

        binding.tagSpinner.adapter = LinkSpinnerAdapter(
            this, resources.getStringArray(R.array.tag_setting).toList()
        )

        binding.tagSpinner.setSelection(2, false)

        binding.tagSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when (position) {
                    0 -> {
                   /*     val dialog = TagModifyDialog()
                        dialog.show(supportFragmentManager, null)
                        binding.tagSpinner.setSelection(2, false)*/
                        showCustomToast("아직 개발 중인 기능입니다.")
                    }

                    1 -> {

           /*             val dialog = RemoveDialog()
                        dialog.show(supportFragmentManager, null)
                        binding.tagSpinner.setSelection(2, false)*/
                        showCustomToast("아직 개발 중인 기능입니다.")
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
                R.id.tag_radio_btn1 -> {
                    val layoutManager = GridLayoutManager(this, 1)
                    binding.tagRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE01

                    binding.tagRadioBtn2.isChecked = false
                }
                R.id.tag_radio_btn2 -> {
                    val layoutManager = GridLayoutManager(this, 2)
                    binding.tagRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE02

                    binding.tagRadioBtn1.isChecked = false
                }
            }
        }
    }

/*    override fun storeFolder(position: Int) {
        val dialog = FolderCreateDialog()
        dialog.show(supportFragmentManager, null)
    }

    override fun modifyLink(position: Int) {
        val intent = Intent(this, LinkModifyActivity::class.java)
        intent.putExtra("modify", tag.linkList[position])
        startActivity(intent)
    }

    override fun removeLink(position: Int) {
        val dialog = RemoveDialog("링크 수정")
        dialog.show(supportFragmentManager, null)
    }*/
}