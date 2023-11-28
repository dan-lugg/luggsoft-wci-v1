package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.await.AwaitCommandHandlerBase
import com.luggsoft.wci.core.process.ProcessDescriptorProvider
import com.luggsoft.wci.core.process.ProcessState

class SelectProcessDescriptorsAwaitCommandHandler(
    private val processDescriptorProvider: ProcessDescriptorProvider,
) : AwaitCommandHandlerBase<SelectProcessDescriptorsAwaitCommandRequest, SelectProcessDescriptorsAwaitCommandResult>()
{
    override fun handle(request: SelectProcessDescriptorsAwaitCommandRequest, context: CommandContext): SelectProcessDescriptorsAwaitCommandResult
    {
        return SelectProcessDescriptorsAwaitCommandResult(
            processDescriptors = this.processDescriptorProvider.getProcessDescriptors(),
        )
    }
}
