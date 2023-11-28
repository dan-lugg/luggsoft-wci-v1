package com.luggsoft.wci.core.commands

interface CommandDispatcher
{
    fun dispatchCommand(request: CommandRequest<*>): CommandResult
}
