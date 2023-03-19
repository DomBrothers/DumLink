package com.dombrothers.android.dumlink.ui

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

    private val testItems1 = arrayListOf(
        Folder("", "덤링크", 10, isSelected = true, isMainFolder = true),
        Folder("", "폴더1", 5)
    )
    private val testItems2 = arrayListOf(
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
    private val testItems3 = arrayListOf(
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", ""),
        Link("", "타이틀", "링크", "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            mainRecyclerFolderList.adapter = folderAdapter
            folderAdapter.setItemList(testItems1)

            val layoutManager = GridLayoutManager(this@MainActivity, 1)
            mainRecyclerLinkList.layoutManager = layoutManager
            mainRecyclerLinkList.adapter = linkAdapter
            linkAdapter.linkViewType = LinkViewType.TYPE01
            linkAdapter.setItemList(testItems2)

            mainRadioBtn1.setOnClickListener {
                onRadioButtonClicked(it)
            }

            mainRadioBtn2.setOnClickListener {
                onRadioButtonClicked(it)
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

    private fun folderListener(folderName: String) {
        // 분기 처리가 추가 되지 않도록 데이터 클래스 구조가 변경 되어야 함
        if (folderName == "덤링크") {
            linkAdapter.setItemList(testItems2)
        } else {
            linkAdapter.setItemList(testItems3)
        }
    }
}
