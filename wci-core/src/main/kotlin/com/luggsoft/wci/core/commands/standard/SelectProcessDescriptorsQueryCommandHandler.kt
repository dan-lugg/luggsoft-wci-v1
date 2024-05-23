package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.query.QueryCommandHandlerBase
import com.luggsoft.wci.core.process.ProcessDescriptorProvider

class SelectProcessDescriptorsQueryCommandHandler(
    private val processDescriptorProvider: ProcessDescriptorProvider,
) : QueryCommandHandlerBase<SelectProcessDescriptorsQueryCommandRequest, SelectProcessDescriptorsQueryCommandResult>()
{
    override fun handle(request: SelectProcessDescriptorsQueryCommandRequest, context: CommandContext): SelectProcessDescriptorsQueryCommandResult
    {
        return SelectProcessDescriptorsQueryCommandResult(
            processDescriptors = this.processDescriptorProvider.getProcessDescriptors(),
        )
    }
}
