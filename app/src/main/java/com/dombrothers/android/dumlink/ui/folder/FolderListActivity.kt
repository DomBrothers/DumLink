package com.dombrothers.android.dumlink.ui.folder

import android.content.Intent
import android.os.Bundle
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.FolderResponse
import com.dombrothers.android.dumlink.data.FolderResponseItem
import com.dombrothers.android.dumlink.data.LinkResponse
import com.dombrothers.android.dumlink.databinding.ActivityFolderListBinding
import com.dombrothers.android.dumlink.ui.adapter.*

class FolderListActivity : BaseActivity<ActivityFolderListBinding>(ActivityFolderListBinding::inflate), FolderListContract.View {
    private val folderAdapter by lazy { FolderAdapter(::folderListener) }
    private val presenter = FolderListPresenter(this)

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }

    override fun setFolderList(folderResponse: FolderResponse) {
        folderAdapter.setItemList(folderResponse)
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllFolder()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            folderListRecyclerLinkList.adapter = folderAdapter

            folderListImgBack.setOnClickListener {
                finish()
            }
        }
    }


    private fun folderListener(folder: FolderResponseItem) {
        val intent = Intent(this, FolderActivity::class.java)
        intent.putExtra("folder", folder)
        startActivity(intent)
    }


}