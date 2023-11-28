package com.luggsoft.wci.core.commands.async

import com.luggsoft.wci.core.commands.CommandResult
import java.util.UUID

interface AsyncCommandResult : CommandResult
{
    val processId: UUID
}
