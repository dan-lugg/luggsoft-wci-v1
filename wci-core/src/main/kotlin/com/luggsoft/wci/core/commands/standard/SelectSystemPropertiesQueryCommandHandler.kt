package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.query.QueryCommandHandlerBase
import com.luggsoft.wci.core.system.InstanceNameProvider
import com.luggsoft.wci.core.system.ServerVersionProvider
import com.luggsoft.wci.core.system.UptimeDurationProvider

class SelectSystemPropertiesQueryCommandHandler(
    private val instanceNameProvider: InstanceNameProvider,
    private val serverVersionProvider: ServerVersionProvider,
    private val uptimeDurationProvider: UptimeDurationProvider,
) : QueryCommandHandlerBase<SelectSystemPropertiesQueryCommandRequest, SelectSystemPropertiesQueryCommandResult>()
{
    override fun handle(request: SelectSystemPropertiesQueryCommandRequest, context: CommandContext): SelectSystemPropertiesQueryCommandResult
    {
        return SelectSystemPropertiesQueryCommandResult(
            instanceName = this.instanceNameProvider.getInstanceName(),
            serverVersion = this.serverVersionProvider.getServerVersion(),
            uptimeDuration = this.uptimeDurationProvider.getUptimeDuration()
        )
    }
}
