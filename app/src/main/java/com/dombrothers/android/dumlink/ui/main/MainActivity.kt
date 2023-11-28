package com.dombrothers.android.dumlink.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RadioButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dombrothers.android.dumlink.R
import com.dombrothers.android.dumlink.base.BaseActivity
import com.dombrothers.android.dumlink.data.*
import com.dombrothers.android.dumlink.databinding.ActivityMainBinding
import com.dombrothers.android.dumlink.ui.adapter.*
import com.dombrothers.android.dumlink.ui.add.LinkAddActivity
import com.dombrothers.android.dumlink.ui.folder.FolderActivity
import com.dombrothers.android.dumlink.ui.folder.FolderListActivity
import com.dombrothers.android.dumlink.ui.login.LoginActivity
import com.dombrothers.android.dumlink.ui.modify.LinkModifyActivity
import com.dombrothers.android.dumlink.ui.tag.TagActivity
import com.dombrothers.android.dumlink.ui.tag.TagListActivity
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.LogoutDialog
import com.dombrothers.android.dumlink.util.RemoveDialog
import com.dombrothers.android.dumlink.util.Util
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainContract.View, LinkItemSpinnerListener {
    private val folderAdapter by lazy { FolderAdapter(::folderListener, true) }
    private val linkAdapter by lazy { LinkAdapter(this) }
    private val tagAdapter by lazy { TagAdapter(::tagListener, true) }
    private val presenter = MainPresenter(this)
    lateinit var mGoogleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        initView()
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllTag()
        presenter.getAllLink()
        presenter.getAllFolder()

    }

    override fun onStop() {
        super.onStop()
        binding.mainEditTxtInputLink.clearFocus()
        binding.mainEditTxtInputLink.text.clear()
    }

    private fun logout() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
    }



    private fun initView() {
        with(binding) {
            mainImgProfile.setOnClickListener {
                val dialog = LogoutDialog(::logout)
                dialog.show(supportFragmentManager, null)
            }

            val tagLayoutManager = FlexboxLayoutManager(this@MainActivity)
            tagLayoutManager.flexDirection = FlexDirection.ROW
            mainRecyclerTagList.layoutManager = tagLayoutManager
            mainRecyclerTagList.adapter = tagAdapter


            binding.mainTxtTagMore.setOnClickListener {
                val intent = Intent(this@MainActivity, TagListActivity::class.java)
                startActivity(intent)
            }

            binding.mainTxtFolderMore.setOnClickListener {
                val intent = Intent(this@MainActivity, FolderListActivity::class.java)
                startActivity(intent)
            }

            mainClContainer.setOnClickListener {
                hideKeyboard()
                mainEditTxtInputLink.clearFocus()
            }
            mainLlFolderAdd.setOnClickListener {
                val dialog = FolderCreateDialog(::createFolder)
                dialog.show(supportFragmentManager, null)
            }

            mainRecyclerFolderList.adapter = folderAdapter

            val layoutManager = GridLayoutManager(this@MainActivity, 2)
            mainRecyclerLinkList.layoutManager = layoutManager
            mainRecyclerLinkList.adapter = linkAdapter
            linkAdapter.linkViewType =  LinkViewType.TYPE02

            mainRadioBtn1.setOnClickListener {
                onRadioButtonClicked(it)
            }

            mainRadioBtn2.setOnClickListener {
                onRadioButtonClicked(it)
            }

            mainTxtAddBtn.setOnClickListener {
                if (Util.extractUrl(mainEditTxtInputLink.text.toString()) == "") {
                    showCustomToast("유효하지 않은 링크입니다.")
                    return@setOnClickListener
                }
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

    override fun setLinkList(linkResponse: LinkResponse) {
        //hideLoading()
        linkAdapter.setItemList(linkResponse)
    }

    override fun setFolderList(folderResponse: FolderResponse) {
        folderAdapter.setItemList(folderResponse)
    }

    override fun setTagList(tagResponse: TagResponse) {
        tagAdapter.setItemList(tagResponse)
    }

    private fun tagListener(tag: String) {
        val intent = Intent(this, TagActivity::class.java)
        intent.putExtra("tag", tag)
        startActivity(intent)
    }

    private fun folderListener(folder: FolderResponseItem) {
        val intent = Intent(this, FolderActivity::class.java)
        intent.putExtra("folder", folder)
        startActivity(intent)
    }

    override fun storeFolder(link: LinkResponseItem) {
        val dialog = FolderCreateDialog(::createFolder)
        dialog.show(supportFragmentManager, null)
    }

    private fun createFolder(folderName: String) {
        presenter.postFolder(folderName)
    }


    override fun modifyLink(link: LinkResponseItem) {
        val intent = Intent(this, LinkModifyActivity::class.java)
        intent.putExtra("modify", link)
        startActivity(intent)
    }

    override fun deleteLinkSuccess() {
        presenter.getAllLink()
    }


    override fun removeLink(link: LinkResponseItem) {
        val dialog = RemoveDialog("링크 삭제", ::deleteLink, link.id!!)
        dialog.show(supportFragmentManager, null)
    }

    fun deleteLink(id: Int) {
        presenter.deleteLink(id)
    }

    override fun postFolderSuccess() {
        presenter.getAllFolder()
    }
}
