package com.luggsoft.wci.core.commands.await

import com.luggsoft.wci.core.commands.CommandHandler

abstract class AwaitCommandHandlerBase<TRequest : AwaitCommandRequest<TResult>, TResult : AwaitCommandResult> : CommandHandler<TRequest, TResult>
