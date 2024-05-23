package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandHandlerBase
import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.CommandRequestDescriptorProvider

class SelectCommandRequestDescriptorsQueryCommandHandler(
    private val commandRequestDescriptorProvider: CommandRequestDescriptorProvider,
) : QueryCommandHandlerBase<SelectCommandRequestDescriptorsQueryCommandRequest, SelectCommandRequestDescriptorsQueryCommandResult>()
{
    override fun handle(request: SelectCommandRequestDescriptorsQueryCommandRequest, context: CommandContext): SelectCommandRequestDescriptorsQueryCommandResult
    {
        return SelectCommandRequestDescriptorsQueryCommandResult(
            commandRequestDescriptors = this.commandRequestDescriptorProvider.getCommandRequestDescriptors(),
        )
    }
}
