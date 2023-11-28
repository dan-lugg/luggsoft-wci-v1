package com.luggsoft.wci.core.util

inline fun <reified TParent> ClassScanner.scanSubClasses(): Set<Class<out TParent>>
{
    try
    {
        return this.scanSubClasses(TParent::class.java)
    } catch (exception: Exception)
    {
        TODO("Handle exception, $exception")
    }
}
