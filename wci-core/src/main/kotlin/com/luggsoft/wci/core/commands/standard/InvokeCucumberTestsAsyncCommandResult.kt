package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.async.AsyncCommandResult
import java.util.UUID

data class InvokeCucumberTestsAsyncCommandResult(
    override val processId: UUID,
) : AsyncCommandResult
