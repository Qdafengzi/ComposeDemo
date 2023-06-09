package com.example.composedemo.utils

import prettify.PrettifyParser
import syntaxhighlight.Parser

class PrettifyHighlighter {
    private val parser: Parser = PrettifyParser()
    fun highlight(fileExtension: String?, sourceCode: String): String {
        val highlighted = StringBuilder()
        val results = parser.parse(fileExtension, sourceCode)
        for (result in results) {
            val type = result.styleKeys[0]
            val content = sourceCode.substring(result.offset, result.offset + result.length)
            highlighted.append(String.format(FONT_PATTERN, getColor(type), content))
        }
        return highlighted.toString()
    }

    private fun getColor(type: String): String? {
        return if (COLORS.containsKey(type)) COLORS[type] else COLORS["pln"]
    }

    companion object {
        private val COLORS = buildColorsMap()
        private const val FONT_PATTERN = "<font color=\"#%s\">%s</font>"
        private fun buildColorsMap(): Map<String, String> {
            val map: MutableMap<String, String> = HashMap()
            map["typ"] = "87cefa"
            map["kwd"] = "00ff00"
            map["lit"] = "ffff00"
            map["com"] = "999999"
            map["str"] = "ff4500"
            map["pun"] = "eeeeee"
            map["pln"] = "ffffff"
            return map
        }
    }
}