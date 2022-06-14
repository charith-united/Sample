package com.architecture.sample.utils

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan

object AppUtils {

    fun generateSpannableString(fullText: String, clickText: String, clickableSpan: ClickableSpan) =
        SpannableString(fullText).apply {
            val startIndex = if (fullText.contains(clickText)) fullText.indexOf(clickText) else 0
            val endIndex = startIndex + clickText.length
            setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
}

