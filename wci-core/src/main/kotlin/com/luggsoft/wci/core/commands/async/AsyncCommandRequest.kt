package com.luggsoft.wci.core.commands.async

import com.luggsoft.wci.core.commands.CommandRequest

interface AsyncCommandRequest<TAsyncCommandResult : AsyncCommandResult> : CommandRequest<TAsyncCommandResult>

