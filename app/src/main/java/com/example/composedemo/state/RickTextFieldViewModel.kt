//package com.example.composedemo.state
//class RickTextFieldViewModel {
//    private val edits = mutableListOf<Edit>()
//    private var _textFieldValue = MutableStateFlow(
//        TextFieldValue(
//            annotatedString = createAnnotatedString(text = "")
//        )
//    )
//
//
//    val textFieldValue: StateFlow<TextFieldValue> = _textFieldValue
//
//    fun updateTextFieldValue(newValue: TextFieldValue) {
//        _textFieldValue.value = newValue.copy(annotatedString = createAnnotatedString(text = newValue.text))
//    }
//
//    fun processEditType(style: Style) {
//        val edit = Edit(
//            range = IntRange(start = selection.start, endInclusive = selection.end),
//            style = style
//        )
//        processEdits(edit)
//        _textFieldValue.value =
//            _textFieldValue.value.copy(annotatedString = createAnnotatedString(text = _textFieldValue.value.text))
//    }
//
//    private fun processEdits(edit: Edit) {
//        // if edit is already contained then remove it as is
//        if (edits.contains(edit)) {
//            edits.remove(edit)
//            return
//        }
//
//        if (edits.isEmpty()) {
//            edits.add(edit)
//            return
//        }
//
//        val processedList = mutableListOf<Edit>()
//        processedList.addAll(edits.filter { it.style != edit.style })
//
//        val sortedList = edits.filter { it.style == edit.style }.toMutableList()
//        if (sortedList.isEmpty()) {
//            edits.add(edit)
//            return
//        }
//
//        var merged = false
//        for (e in sortedList) {
//            if (edit.range.last <= e.range.first || edit.range.first >= e.range.last) {
//                processedList.add(e)
//            } else {
//                merged = true
//                processedList.add(
//                    Edit(
//                        style = edit.style,
//                        range = IntRange(
//                            start = e.range.first.coerceAtMost(edit.range.first),
//                            endInclusive = e.range.last.coerceAtLeast(edit.range.last)
//                        )
//                    )
//                )
//            }
//        }
//
//        if (!merged) {
//            processedList.add(edit)
//        }
//
//        edits.clear()
//        edits.addAll(processedList)
//    }
//
//    private fun createAnnotatedString(text: String): AnnotatedString = when (edits.size) {
//        0 -> processVisualAnnotationsOnString(text = text)
//        else -> processEditsOnString(text = processVisualAnnotationsOnString(text = text))
//    }
//
//    private fun processEditsOnString(text: AnnotatedString): AnnotatedString {
//        val spanStyles = text.spanStyles.toMutableList()
//        for (edit in edits) {
//            spanStyles.add(
//                AnnotatedString.Range(
//                    getStyle(style = edit.style),
//                    start = edit.range.first,
//                    end = edit.range.last
//                )
//            )
//        }
//
//        return AnnotatedString(text = text.text, spanStyles = spanStyles)
//    }
//
//    private fun processVisualAnnotationsOnString(text: String): AnnotatedString {
//        val builder = AnnotatedString.Builder()
//        val matches = HASHTAG_REGEX_PATTERN.findAll(text)
//        var startIndex = 0
//        for (match in matches) {
//            // append everything before match
//            builder.append(text.substring(startIndex = startIndex, endIndex = match.range.first))
//
//            // append hashtag style
//            builder.withStyle(style = getStyle(style = Style.HASHTAG)) {
//                append(text.substring(range = match.range))
//            }
//            startIndex = match.range.last + 1
//        }
//        if (startIndex <= text.length - 1) {
//            builder.append(text.substring(startIndex = startIndex, endIndex = text.length))
//        }
//        return builder.toAnnotatedString()
//    }
//
//    private fun getStyle(style: Style): SpanStyle = when (style) {
//        Style.HASHTAG -> RichTextFieldStyles.HashTags.toSpanStyle()
//        Style.BOLD -> RichTextFieldStyles.Bold.toSpanStyle()
//        Style.HEADING -> RichTextFieldStyles.Heading.toSpanStyle()
//        Style.ITALICS -> RichTextFieldStyles.Italics.toSpanStyle()
//        Style.TITLE -> RichTextFieldStyles.Title.toSpanStyle()
//        Style.SUBHEADING -> RichTextFieldStyles.SubHeading.toSpanStyle()
//        Style.BODY -> RichTextFieldStyles.Body.toSpanStyle()
//    }
//}
