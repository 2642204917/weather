package com.sunnyweather.android.ui.binding

import android.view.inputmethod.EditorInfo
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("query")
fun TextInputEditText.query(function: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE, EditorInfo.IME_ACTION_GO, EditorInfo.IME_ACTION_SEARCH -> {
                function()
                true
            }

            else -> false
        }

    }


}