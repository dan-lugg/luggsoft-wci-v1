package com.luggsoft.wci.core.util

import java.lang.reflect.ParameterizedType

val Class<*>.typeParameterClasses: List<Class<*>>
    get()
    {
        try
        {
            return when (val genericSuperClass = this.genericSuperclass)
            {
                is ParameterizedType -> genericSuperClass.actualTypeArguments
                    .map { type -> type as Class<*> }
                
                else -> throw UnsupportedOperationException("Failed to resolve type parameter classes for $this, superclass is not parameterized")
            }
        } catch (exception: ClassCastException)
        {
            throw UnsupportedOperationException("Failed to resolve type parameter classes for $this", exception)
        }
    }
