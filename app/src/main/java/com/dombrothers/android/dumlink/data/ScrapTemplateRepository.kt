package com.dombrothers.android.dumlink.data



class ScrapTemplateRepository {
    private val scrapTemplateModel = ScrapTemplateModel()

    fun handleSendText(title: String): Pair<String, Map<String, String>> {
        return scrapTemplateModel.handleSendText(title)
    }
}
