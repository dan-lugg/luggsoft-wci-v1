package com.luggsoft.wci.core.commands.query

import com.luggsoft.wci.core.commands.CommandRequest

interface QueryCommandRequest<TAwaitCommandResult : QueryCommandResult> : CommandRequest<TAwaitCommandResult>
