package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.commands.async.AsyncCommandHandlerBase
import com.luggsoft.wci.core.commands.await.AwaitCommandHandlerBase

abstract class CommandDispatcherBase : CommandDispatcher
{
    protected abstract val contextFactory: CommandContextFactory
    protected abstract val executorFactory: CommandHandlerResolver
    
    override fun dispatchCommand(request: CommandRequest<*>): CommandResult
    {
        val context = this.contextFactory.createContext()
        
        val result = when (val handler = this.getHandler(request::class.java))
        {
            is AwaitCommandHandlerBase<*, *> -> handler.handle(request, context)
            is AsyncCommandHandlerBase<*, *> -> handler.handle(request, context)
            else -> TODO("Couldn't resolve handler for $request")
        }
        
        return result
    }
    
    protected abstract fun getHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
}
