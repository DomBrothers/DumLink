package com.dombrothers.android.dumlink.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.Folder
import com.dombrothers.android.dumlink.data.Link
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainContract.View {
    private val folderAdapter by lazy { FolderAdapter(::folderListener) }
    private val linkAdapter by lazy { LinkAdapter() }

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
        Folder("", "폴더1", 10, testLinks1),
        Folder("", "폴더2", 5, testLinks2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            mainRecyclerFolderList.adapter = folderAdapter
            folderAdapter.setItemList(testItems)

            val layoutManager = GridLayoutManager(this@MainActivity, 1)
            mainRecyclerLinkList.layoutManager = layoutManager
            mainRecyclerLinkList.adapter = linkAdapter
            linkAdapter.linkViewType = LinkViewType.TYPE01
            linkAdapter.setItemList(testLinks1)

            mainRadioBtn1.setOnClickListener {
                onRadioButtonClicked(it)
            }

            mainRadioBtn2.setOnClickListener {
                onRadioButtonClicked(it)
            }

            mainTxtAddBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, LinkAddActivity::class.java)
                intent.putExtra("add", mainEditTxtInputLink.text.toString()) // 정규식으로 검증 후 요청
                startActivity(intent)
            }
        }
    }

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            when (view.getId()) {
                R.id.main_radio_btn1 -> {
                    val layoutManager = GridLayoutManager(this, 1)
                    binding.mainRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE01

                    binding.mainRadioBtn2.isChecked = false
                }
                R.id.main_radio_btn2 -> {
                    val layoutManager = GridLayoutManager(this, 2)
                    binding.mainRecyclerLinkList.layoutManager = layoutManager
                    linkAdapter.linkViewType = LinkViewType.TYPE02

                    binding.mainRadioBtn1.isChecked = false
                }
            }
        }
    }

    private fun folderListener(folder: Folder) {
        val intent = Intent(this, FolderActivity::class.java)
        intent.putExtra("folder", folder)
        startActivity(intent)

    }
}
