package com.luggsoft.wci.core.commands.await

import com.luggsoft.wci.core.commands.CommandRequest

interface AwaitCommandRequest<TAwaitCommandResult : AwaitCommandResult> : CommandRequest<TAwaitCommandResult>
