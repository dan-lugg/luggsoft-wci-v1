package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.search.EntityProvider

interface CommandRequestDescriptorProvider : EntityProvider<CommandRequestDescriptor>
{
    fun getCommandRequestDescriptors(): List<CommandRequestDescriptor>
}

