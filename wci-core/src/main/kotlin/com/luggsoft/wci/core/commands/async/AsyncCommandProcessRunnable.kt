package com.luggsoft.wci.core.commands.async

import com.luggsoft.wci.core.commands.CommandContext

interface AsyncCommandProcessRunnable<TRequest : AsyncCommandRequest<*>, TContext : CommandContext> : Runnable
