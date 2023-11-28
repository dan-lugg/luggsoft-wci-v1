package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandResult
import com.luggsoft.wci.core.process.ProcessDescriptor

data class SelectProcessDescriptorsAwaitCommandResult(
    val processDescriptors: List<ProcessDescriptor>
) : AwaitCommandResult
