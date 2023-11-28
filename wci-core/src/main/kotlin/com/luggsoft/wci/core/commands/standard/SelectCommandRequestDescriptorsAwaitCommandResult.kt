package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandResult
import com.luggsoft.wci.core.commands.CommandRequestDescriptor

data class SelectCommandRequestDescriptorsAwaitCommandResult(
    val commandRequestDescriptors: List<CommandRequestDescriptor>
) : AwaitCommandResult
