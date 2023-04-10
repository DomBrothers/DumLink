package com.dombrothers.android.dumlink.data

import android.util.Log.d
import org.jsoup.Jsoup
import java.util.regex.Matcher
import java.util.regex.Pattern

class ScrapTemplateModel {
    private fun extractUrl(content: String?): String {
        return try {
            val regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
            val p: Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
            val m: Matcher = p.matcher(content)
            if (m.find()) {
                m.group()
            } else ""
        } catch (e: Exception) {
            ""
        }
    }

    fun handleSendText(title: String): Pair<String, Map<String, String>> {
        val ogMap = mutableMapOf<String, String>()
        val url = extractUrl(title)

        val document = Jsoup.connect(url).userAgent("Android").get()
        val elements = document.select("meta[property^=og:]")

        elements?.forEach { el ->
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
