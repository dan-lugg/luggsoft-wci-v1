package com.luggsoft.wci.core.util

infix fun String?.ifNullOrEmpty(default: String): String
{
    if (this.isNullOrEmpty())
    {
        return default
    }
    
    return this
}
