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
import com.dombrothers.android.dumlink.databinding.ActivityTagListBinding
import com.dombrothers.android.dumlink.ui.adapter.*
import com.dombrothers.android.dumlink.util.FolderCreateDialog
import com.dombrothers.android.dumlink.util.FolderModifyDialog
import com.dombrothers.android.dumlink.util.RemoveDialog
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class TagListActivity : BaseActivity<ActivityTagListBinding>(ActivityTagListBinding::inflate) {
    private val tagAdapter by lazy { TagAdapter(::tagListener) }
    private val testLinks1 = arrayListOf(
        Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/LQlhRXE3Ixze7tHJTynqF",
            "색감을 잘 활용하는 여행 인플루언서 [진소라, 미네쿠, 솨진관]",
            "https://supple.kr/feed/clhk5n8lv000sbm0svxuswulj",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/vJnt7lQsdxHegPxiQUbmH",
            "트위터에서 화제성에 탑승하는 법! [올리브영]",
            "https://supple.kr/feed/clhk5i96x000qbm0sxeu2nwq7",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/Ic8yxAkmEz3EXAFaw7y9J",
            "세대별 유튜버 특징이 궁금하다면? 유튜브 채널의 변천사 공개!",
            "https://supple.kr/feed/clhk5cds0000obm0sbr0iyk54",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/rBY2YxiuKcfxX6PZss2WQ",
            "어느 요일, 몇 시에 뉴스레터를 보내야 할까요?",
            "https://supple.kr/feed/clhk59p5i000kbm0sqe0pnuvv",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/M3tuQnY4zNY_dlEkZonmp",
            "[주간호]\uD83E\uDDEA인턴은 심심하다 (고민/업무꿀팁)",
            "https://supple.kr/feed/clhk540uk000ibm0s9vr7ud58",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/QdvXS6J0ZenUkFoX7aE7n",
            "믿고 장기투자? 한국에서 그랬다간 폭망하는 이유...'한국형 탑다운 투자 전략'",
            "https://supple.kr/feed/clhk3mfms000ebm0siumk47oh",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/TRzqsltUvhHyeebUQ04Ll",
            "[해외주식] AI에서 갈라선 버핏과 빅테크",
            "https://supple.kr/feed/clhk3jes3000cbm0s9sxx1r5p",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/HA8J_-XcQsomAu7FX0ZQk",
            "“서울 사람들이 사러 왔어요”…집값 폭락한 ‘이 곳’ 북적이는 사연",
            "https://supple.kr/feed/clhk2u58y0004bm0su8hshsqj",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/flVFX5LGqCLq4F-VDyWG1",
            "ChatGPT가 뤼튼을 FOMO에 빠뜨리다",
            "https://supple.kr/feed/clhjz1ah6002y7b0t1nds05xj",
            ""
        ), Link(
            "https://res.cloudinary.com/linkareer/image/fetch/f_auto,c_thumb,w_500,h_250/https://supple-attachment.s3.ap-northeast-2.amazonaws.com/post-thumbnail/cbRHi67TRHv0Nii9JcsOD",
            "이동채 에코프로 회장, \"급할수록 돌아가라\" 잊었나...오너리스크 '비상'",
            "https://supple.kr/feed/clhjymae3002w7b0toi6judbz",
            ""
        )
    )
    private val testTags = arrayListOf(
        Tag("Java", testLinks1),
        Tag("Kotlin", testLinks1),
        Tag("자료구조", testLinks1),
        Tag("클린아키텍처", testLinks1),
        Tag("chatGPT", testLinks1),
        Tag("운동", testLinks1),
        Tag("유튜브", testLinks1),
        Tag("소프트웨어공학론", testLinks1),
        Tag("IT", testLinks1),
        Tag("응용소프트웨어 프로그래밍", testLinks1),
        Tag("게임", testLinks1),
        Tag("스타트업", testLinks1),
        Tag("뉴스", testLinks1),
        Tag("손흥민", testLinks1),
        Tag("카카오", testLinks1),
        Tag("네이버", testLinks1),
        Tag("토스", testLinks1),
        Tag("자기소개서", testLinks1),
        Tag("포트폴리오", testLinks1),
        Tag("영화", testLinks1),
        Tag("넷플릭스", testLinks1),
        Tag("드라마", testLinks1)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            val tagLayoutManager = FlexboxLayoutManager(this@TagListActivity)
            tagLayoutManager.flexDirection = FlexDirection.ROW
            tagListRecyclerLinkList.layoutManager = tagLayoutManager
            tagListRecyclerLinkList.adapter = tagAdapter
            tagAdapter.setItemList(testTags)

            tagListImgBack.setOnClickListener {
                finish()
            }
        }
    }


    private fun tagListener(tag: Tag) {
        val intent = Intent(this, TagActivity::class.java)
        intent.putExtra("tag", tag)
        startActivity(intent)
    }

}