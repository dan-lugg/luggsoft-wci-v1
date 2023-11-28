package com.luggsoft.wci.core

import com.luggsoft.wci.core.system.Version

interface Module
{
    val version: Version
    
    fun configureModule(context: ModuleContext)
}
