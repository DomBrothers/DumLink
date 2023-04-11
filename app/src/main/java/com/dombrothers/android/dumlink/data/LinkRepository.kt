package com.dombrothers.android.dumlink.data



class LinkRepository {
    private val linkScrap = LinkScrap()

    fun handleSendText(title: String): Pair<String, Map<String, String>> {
        return linkScrap.handleSendText(title)
    }
}
