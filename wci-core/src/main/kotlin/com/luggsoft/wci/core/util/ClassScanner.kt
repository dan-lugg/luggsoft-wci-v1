package com.luggsoft.wci.core.util

interface ClassScanner
{
    fun <TParent> scanSubClasses(parentClass: Class<out TParent>): Set<Class<out TParent>>
}
