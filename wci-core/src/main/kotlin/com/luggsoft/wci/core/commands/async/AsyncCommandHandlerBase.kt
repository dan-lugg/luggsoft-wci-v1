package com.luggsoft.wci.core.commands.async

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.CommandHandler
import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.process.ProcessContext
import com.luggsoft.wci.core.process.ProcessDescriptor
import com.luggsoft.wci.core.process.ProcessManager
import com.luggsoft.wci.core.process.ProcessFunction
import java.util.UUID

abstract class AsyncCommandHandlerBase<TRequest : AsyncCommandRequest<TResult>, TResult : AsyncCommandResult> : CommandHandler<TRequest, TResult>
{
    protected abstract val processManager: ProcessManager
    
    final override fun handle(request: TRequest, context: CommandContext): TResult
    {
        val processContext = object : ProcessContext
        {
            override val processId: UUID
                get() = TODO("Not yet implemented")
            
            override fun sendNotification(notification: Notification<*>)
            {
                TODO("Not yet implemented")
            }
        }
        
        val processFunction = this.createProcessFunction()
        val processDescriptor = this.processManager.startProcess(processContext, processFunction)
        
        return createCommandResult(processDescriptor)
    }
    
    protected abstract fun createProcessFunction(): ProcessFunction
    
    protected abstract fun createCommandResult(processDescriptor: ProcessDescriptor): TResult
}
