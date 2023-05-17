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
import com.dombrothers.android.dumlink.databinding.DialogFolderCreateBinding
import com.dombrothers.android.dumlink.ui.adapter.FolderAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkAdapter
import com.dombrothers.android.dumlink.ui.adapter.LinkItemSpinnerListener
import com.dombrothers.android.dumlink.ui.adapter.LinkViewType
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.RemoveDialog
import com.dombrothers.android.dumlink.util.Util

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    MainContract.View, LinkItemSpinnerListener {
    private val folderAdapter by lazy { FolderAdapter(::folderListener) }
    private val linkAdapter by lazy { LinkAdapter(this) }

    private val testLinks1 = arrayListOf(
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/LQlhRXE3Ixze7tHJTynqF", "색감을 잘 활용하는 여행 인플루언서 [진소라, 미네쿠, 솨진관]", "https://supple.kr/feed/clhk5n8lv000sbm0svxuswulj", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/vJnt7lQsdxHegPxiQUbmH", "트위터에서 화제성에 탑승하는 법! [올리브영]", "https://supple.kr/feed/clhk5i96x000qbm0sxeu2nwq7", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/Ic8yxAkmEz3EXAFaw7y9J", "세대별 유튜버 특징이 궁금하다면? 유튜브 채널의 변천사 공개!", "https://supple.kr/feed/clhk5cds0000obm0sbr0iyk54", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/rBY2YxiuKcfxX6PZss2WQ", "어느 요일, 몇 시에 뉴스레터를 보내야 할까요?", "https://supple.kr/feed/clhk59p5i000kbm0sqe0pnuvv", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/M3tuQnY4zNY_dlEkZonmp", "[주간호]\uD83E\uDDEA인턴은 심심하다 (고민/업무꿀팁)", "https://supple.kr/feed/clhk540uk000ibm0s9vr7ud58", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/QdvXS6J0ZenUkFoX7aE7n", "믿고 장기투자? 한국에서 그랬다간 폭망하는 이유...'한국형 탑다운 투자 전략'", "https://supple.kr/feed/clhk3mfms000ebm0siumk47oh", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/TRzqsltUvhHyeebUQ04Ll", "[해외주식] AI에서 갈라선 버핏과 빅테크", "https://supple.kr/feed/clhk3jes3000cbm0s9sxx1r5p", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/HA8J_-XcQsomAu7FX0ZQk", "“서울 사람들이 사러 왔어요”…집값 폭락한 ‘이 곳’ 북적이는 사연", "https://supple.kr/feed/clhk2u58y0004bm0su8hshsqj", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/flVFX5LGqCLq4F-VDyWG1", "ChatGPT가 뤼튼을 FOMO에 빠뜨리다", "https://supple.kr/feed/clhjz1ah6002y7b0t1nds05xj", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/cbRHi67TRHv0Nii9JcsOD", "이동채 에코프로 회장, \"급할수록 돌아가라\" 잊었나...오너리스크 '비상'", "https://supple.kr/feed/clhjymae3002w7b0toi6judbz", "")
    )
    private val testLinks2 = arrayListOf(
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/5cD8wIkGKKgNTwQA-SaE7", "제2의 전성기를 맞은 OOH 광고", "https://supple.kr/feed/clhjydtq817201223b12tqbszpp", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/fvrLNER-msfZ7ESOLqfco", "스타트업의 법적 이슈, 어떻게 대처해야 할까?", "https://supple.kr/feed/clhjwtlv6002g7b0t90a3bteg", ""),
        Link("https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/F0cpPTBT1BhbTGyVqb8kp", "카페 사장 41%가 2030… 부동산중개소 70%가 5060", "https://supple.kr/feed/clhjw5ix6002c7b0t8hx01pik", "")
    )

    private val testItems = arrayListOf(
        Folder("", "공부할 것", 10, testLinks1), Folder("", "과제 참고 링크", 5, testLinks2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            mainClContainer.setOnClickListener {
                hideKeyboard()
                mainEditTxtInputLink.clearFocus()
            }
            mainLlFolderAdd.setOnClickListener {
                val dialog = FolderCreateDialog()
                dialog.show(supportFragmentManager, null)
            }

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

    private fun folderListener(folder: Folder) {
        val intent = Intent(this, FolderActivity::class.java)
        intent.putExtra("folder", folder)
        startActivity(intent)
    }

    override fun storeFolder(position: Int) {
        val dialog = FolderCreateDialog()
        dialog.show(supportFragmentManager, null)
    }

    override fun modifyLink(position: Int) {
        val intent = Intent(this, LinkModifyActivity::class.java)
        intent.putExtra("modify", testLinks1[position])
        startActivity(intent)
    }

    override fun removeLink(position: Int) {
        val dialog = RemoveDialog("링크 수정")
        dialog.show(supportFragmentManager, null)
    }
}
