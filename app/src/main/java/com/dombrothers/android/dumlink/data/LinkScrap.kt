package com.dombrothers.android.dumlink.data

import com.dombrothers.android.dumlink.util.Util.extractUrl
import org.jsoup.Jsoup
import java.util.regex.Matcher
import java.util.regex.Pattern

class LinkScrap {
    fun handleSendText(title: String): Pair<String, Map<String, String>> {
        val ogMap = mutableMapOf<String, String>()
        val url = extractUrl(title)

        val document = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36").get()
        val elements = document.select("meta[property^=og:]")

        elements.forEach { el ->
            when (el.attr("property")) {
                "og:url" -> {
                    el.attr("content")?.let { content ->
                        ogMap.put("url", content)
                    }
                }
                "og:site_name" -> {
                    el.attr("content")?.let { content ->
                        ogMap.put("siteName", content)
                    }
                }
                "og:title" -> {
                    el.attr("content")?.let { content ->
                        ogMap.put("title", content)
                    }
                }
                "og:description" -> {
                    el.attr("content")?.let { content ->
                        ogMap.put("description", content)
                    }
                }
                "og:image" -> {
                    ogMap.put("image", el.attr("content"))
                }
            }
        }
        return Pair(url, ogMap)
    }
}
