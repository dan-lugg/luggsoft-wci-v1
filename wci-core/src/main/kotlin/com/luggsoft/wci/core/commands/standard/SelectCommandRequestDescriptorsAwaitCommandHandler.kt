package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandHandlerBase
import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.CommandRequestDescriptorProvider

class SelectCommandRequestDescriptorsAwaitCommandHandler(
    private val commandRequestDescriptorProvider: CommandRequestDescriptorProvider,
) : AwaitCommandHandlerBase<SelectCommandRequestDescriptorsAwaitCommandRequest, SelectCommandRequestDescriptorsAwaitCommandResult>()
{
    override fun handle(request: SelectCommandRequestDescriptorsAwaitCommandRequest, context: CommandContext): SelectCommandRequestDescriptorsAwaitCommandResult
    {
        return SelectCommandRequestDescriptorsAwaitCommandResult(
            commandRequestDescriptors = this.commandRequestDescriptorProvider.getCommandRequestDescriptors(),
        )
    }
}
