package com.luggsoft.wci.core.util

infix fun String.ifBlank(default: String): String
{
    return this ifNullOrBlank default
}
