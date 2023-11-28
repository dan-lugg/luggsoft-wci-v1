package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.await.AwaitCommandHandlerBase
import com.luggsoft.wci.core.system.InstanceNameProvider
import com.luggsoft.wci.core.system.ServerVersionProvider
import com.luggsoft.wci.core.system.UptimeDurationProvider

class SelectSystemPropertiesAwaitCommandHandler(
    private val instanceNameProvider: InstanceNameProvider,
    private val serverVersionProvider: ServerVersionProvider,
    private val uptimeDurationProvider: UptimeDurationProvider,
) : AwaitCommandHandlerBase<SelectSystemPropertiesAwaitCommandRequest, SelectSystemPropertiesAwaitCommandResult>()
{
    override fun handle(request: SelectSystemPropertiesAwaitCommandRequest, context: CommandContext): SelectSystemPropertiesAwaitCommandResult
    {
        return SelectSystemPropertiesAwaitCommandResult(
            instanceName = this.instanceNameProvider.getInstanceName(),
            serverVersion = this.serverVersionProvider.getServerVersion(),
            uptimeDuration = this.uptimeDurationProvider.getUptimeDuration()
        )
    }
}
