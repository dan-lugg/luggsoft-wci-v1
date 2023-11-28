package com.luggsoft.wci.core.commands.async

interface AsyncCommandProcessManager
{
    fun startCommandProcess(runnable: AsyncCommandProcessRunnable<*, *>): AsyncCommandProcessDescriptor
}

