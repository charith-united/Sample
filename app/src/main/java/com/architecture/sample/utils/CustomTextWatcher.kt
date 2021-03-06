package com.architecture.sample.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class CustomTextWatcher(
    private val textInputLayout: TextInputLayout?,
    private val errorText: String,
    private val regexValue: String,
    private val regex: Regex = regexValue.toRegex()
): TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        textInputLayout?.error = when(editable?.toString()?.matches(regex)){
            true -> null
            else -> errorText
        }
    }
}