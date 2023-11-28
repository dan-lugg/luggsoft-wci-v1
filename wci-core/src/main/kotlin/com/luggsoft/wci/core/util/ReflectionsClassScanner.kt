package com.luggsoft.wci.core.util

import org.reflections.Reflections

class ReflectionsClassScanner(
    private val reflections: Reflections,
) : ClassScanner
{
    override fun <TParent> scanSubClasses(parentClass: Class<out TParent>): Set<Class<out TParent>>
    {
        try
        {
            return this.reflections.getSubTypesOf(parentClass)
        } catch (exception: Exception)
        {
            TODO("Handle exception, $exception")
        }
    }
}
