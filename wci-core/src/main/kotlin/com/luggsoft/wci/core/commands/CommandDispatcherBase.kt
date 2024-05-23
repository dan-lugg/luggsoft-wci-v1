package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.commands.async.AsyncCommandHandlerBase
import com.luggsoft.wci.core.commands.query.QueryCommandHandlerBase

abstract class CommandDispatcherBase : CommandDispatcher
{
    protected abstract val contextFactory: CommandContextFactory
    protected abstract val executorFactory: CommandHandlerResolver
    
    override fun dispatchCommand(request: CommandRequest<*>): CommandResult
    {
        val context = this.contextFactory.createContext()
        
        return when (val handler = this.getHandler(request::class.java))
        {
            is QueryCommandHandlerBase<*, *> -> handler.handle(request, context)
            is AsyncCommandHandlerBase<*, *> -> handler.handle(request, context)
            else -> TODO("Couldn't resolve handler for $request")
        }
    }
    
    protected abstract fun getHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
}
