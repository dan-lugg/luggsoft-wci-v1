package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.async.AsyncCommandHandlerBase
import com.luggsoft.wci.core.notifications.NotificationLevel
import com.luggsoft.wci.core.process.ProcessDescriptor
import com.luggsoft.wci.core.process.ProcessFunction
import com.luggsoft.wci.core.process.ProcessManager
import java.util.UUID

class InvokeCucumberTestsAsyncCommandHandler(
    override val processManager: ProcessManager,
) : AsyncCommandHandlerBase<InvokeCucumberTestsAsyncCommandRequest, InvokeCucumberTestsAsyncCommandResult>()
{
    override fun createCommandResult(processDescriptor: ProcessDescriptor): InvokeCucumberTestsAsyncCommandResult
    {
        return InvokeCucumberTestsAsyncCommandResult(
            processId = UUID.randomUUID(),
        )
    }
    
    override fun createProcessFunction(): ProcessFunction
    {
        return ProcessFunction {
            Thread.sleep(10_000);
            return@ProcessFunction NotificationLevel.SUCCESS
        }
    }
}
