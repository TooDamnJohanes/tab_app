package com.jvcoding.tab_app.utils

object TabUtils {

    fun <T> requireBinding(binding: T, block: T.() -> Unit) {
        binding.apply(block)
    }

}