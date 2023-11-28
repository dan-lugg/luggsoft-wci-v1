package com.luggsoft.wci.core.util

infix fun String.ifEmpty(default: String): String
{
    return this ifNullOrEmpty default
}
