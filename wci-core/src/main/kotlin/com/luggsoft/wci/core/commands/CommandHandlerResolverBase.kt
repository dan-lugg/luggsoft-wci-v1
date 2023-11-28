package com.luggsoft.wci.core.commands

abstract class CommandHandlerResolverBase : CommandHandlerResolver
{
    private val handlerCacheMap = mutableMapOf<Class<*>, CommandHandler<CommandRequest<*>, *>>()
    
    override fun getCommandHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
    {
        return this.handlerCacheMap.getOrPut(requestClass) {
            return@getOrPut this.createCommandHandler(requestClass)
        }
    }
    
    protected abstract fun createCommandHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
}
