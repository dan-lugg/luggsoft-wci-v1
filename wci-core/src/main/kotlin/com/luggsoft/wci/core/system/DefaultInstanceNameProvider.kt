package com.luggsoft.wci.core.system

class DefaultInstanceNameProvider : InstanceNameProvider
{
    override fun getInstanceName(): String
    {
        return "wci-example"
    }
}
