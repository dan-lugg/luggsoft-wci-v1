package com.luggsoft.wci.core.commands.query

import com.luggsoft.wci.core.commands.CommandHandler

abstract class QueryCommandHandlerBase<TRequest : QueryCommandRequest<TResult>, TResult : QueryCommandResult> : CommandHandler<TRequest, TResult>
