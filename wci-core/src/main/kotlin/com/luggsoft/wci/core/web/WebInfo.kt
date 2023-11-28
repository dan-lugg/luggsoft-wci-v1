package com.luggsoft.wci.core.web

import com.luggsoft.wci.core.util.EMPTY_STRING

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class WebInfo(
    val title: String,
    val description: String = EMPTY_STRING,
    val isSystem: Boolean = false,
)
