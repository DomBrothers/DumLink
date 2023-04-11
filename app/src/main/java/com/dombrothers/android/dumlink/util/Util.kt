package com.dombrothers.android.dumlink.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class Util {
    companion object {
        fun extractUrl(content: String): String {
            return try {
                val regex =
                    "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
                val p: Pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
                val m: Matcher = p.matcher(content)
                if (m.find()) m.group()
                else ""
            } catch (e: Exception) {
                ""
            }
        }
    }
}