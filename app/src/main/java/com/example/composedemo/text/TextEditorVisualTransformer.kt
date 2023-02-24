package com.example.composedemo.text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.unit.sp

val HASHTAG_REGEX_PATTERN = Regex(pattern = "(#[A-Za-z0-9-_]+)(?:#[A-Za-z0-9-_]+)*")
val BOLD_REGEX_PATTERN = Regex(pattern = "(\\*{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\*{2})")
val ITALICS_REGEX_PATTERN = Regex(pattern = "(\\~{2})(\\s*\\b)([^\\*]*)(\\b\\s*)(\\~{2})")
val HEADING_REGEX_PATTERN = Regex(pattern = "\\#{1,4}\\s([^\\#]*)\\s\\#{1,4}(?=\\n)")

class TextEditorVisualTransformer : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        var transformation = transformBold(text = text)
        transformation = transformItalics(text = transformation.annotatedString)
        transformation = transformHashtags(text = transformation.annotatedString)
        transformation = transformHeading(text = transformation.annotatedString)

        return convertToTransformedText(transformation)
    }

    private fun convertToTransformedText(transformation: Transformation): TransformedText {
        return TransformedText(text = transformation.annotatedString, offsetMapping = transformation.offsetMapping)
    }
}

class Transformation(
    /**
     * The transformed text
     */
    val annotatedString: AnnotatedString,

    /**
     * The map used for bidirectional offset mapping from original to transformed text.
     */
    val offsetMapping: OffsetMapping
)

fun transformHeading(text: AnnotatedString): Transformation {
    val matches = HEADING_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            val headingLevel = getHeadingLevel(match.value)
            val sizeList = listOf(32.sp, 28.sp, 24.sp, 18.sp)
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = sizeList[headingLevel - 1] / 4),
                matchRange.first,
                matchRange.first + headingLevel
            )
            builder.addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = sizeList[headingLevel - 1]),
                matchRange.first + headingLevel,
                matchRange.last - headingLevel + 1
            )
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = sizeList[headingLevel - 1] / 4),
                matchRange.last - headingLevel + 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformItalics(text: AnnotatedString): Transformation {
    val matches = ITALICS_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = 10.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontStyle = FontStyle.Italic), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = 10.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformBold(text: AnnotatedString): Transformation {
    val matches = BOLD_REGEX_PATTERN.findAll(text.text)
    return if (matches.count() > 0) {
        val builder = AnnotatedString.Builder(text)
        for (match in matches) {
            val matchRange = match.range
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = 10.sp),
                matchRange.first,
                matchRange.first + 2
            )
            builder.addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), matchRange.first + 2, matchRange.last - 1)
            builder.addStyle(
                style = SpanStyle(color = Color.Gray, baselineShift = BaselineShift.Superscript, fontSize = 10.sp),
                matchRange.last - 1,
                matchRange.last + 1
            )
        }
        Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
    } else {
        Transformation(annotatedString = text, offsetMapping = OffsetMapping.Identity)
    }
}

fun transformHashtags(text: AnnotatedString): Transformation {
    val builder = AnnotatedString.Builder(text)
    val matches = HASHTAG_REGEX_PATTERN.findAll(text.text)
    for (match in matches) {
        val matchRange = match.range
        builder.addStyle(style = SpanStyle(color = Color.Yellow), start = matchRange.first, end = matchRange.last + 1)
    }
    return Transformation(annotatedString = builder.toAnnotatedString(), offsetMapping = OffsetMapping.Identity)
}

private fun getHeadingLevel(text: String): Int {
    var i = 0
    while (i < text.length) {
        if (text[i] == '#') {
            i++
        } else {
            break
        }
    }
    return i
}
