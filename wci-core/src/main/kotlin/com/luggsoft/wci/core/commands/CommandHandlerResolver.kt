package com.luggsoft.wci.core.commands

interface CommandHandlerResolver
{
    fun getCommandHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
}
