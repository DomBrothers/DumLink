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
import com.dombrothers.android.dumlink.data.Tag
import com.dombrothers.android.dumlink.databinding.ActivityFolderBinding
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding
import com.dombrothers.android.dumlink.databinding.ActivityTagBinding
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkItemSpinnerListener
import com.dombrothers.android.dumlink.ui.adapter.LinkSpinnerAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.FolderModifyDialog
import com.dombrothers.android.dumlink.util.RemoveDialog
import com.dombrothers.android.dumlink.util.TagModifyDialog

class TagActivity : BaseActivity<ActivityTagBinding>(ActivityTagBinding::inflate),
    LinkItemSpinnerListener  {
    private val linkAdapter by lazy { LinkAdapter(this) }
    private lateinit var tag: Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("tag", Tag::class.java)!!
        } else {
            intent.getParcelableExtra("tag")!!
        }


        binding.tagTxtTitle.text = tag.tagName

        val layoutManager = GridLayoutManager(this@TagActivity, 1)
        binding.tagRecyclerLinkList.layoutManager = layoutManager
        binding.tagRecyclerLinkList.adapter = linkAdapter
        linkAdapter.linkViewType = LinkViewType.TYPE01
        linkAdapter.setItemList(tag.linkList)

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
                        val dialog = TagModifyDialog()
                        dialog.show(supportFragmentManager, null)
                        binding.tagSpinner.setSelection(2, false)
                    }

                    1 -> {
                        val dialog = RemoveDialog("태그 삭제")
                        dialog.show(supportFragmentManager, null)
                        binding.tagSpinner.setSelection(2, false)
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

    override fun storeFolder(position: Int) {
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
    }
}