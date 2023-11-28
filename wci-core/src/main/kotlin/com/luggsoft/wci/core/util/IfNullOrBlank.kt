package com.luggsoft.wci.core.util

infix fun String?.ifNullOrBlank(default: String): String
{
    if (this.isNullOrBlank())
    {
        return default
    }
    
    return this
}
