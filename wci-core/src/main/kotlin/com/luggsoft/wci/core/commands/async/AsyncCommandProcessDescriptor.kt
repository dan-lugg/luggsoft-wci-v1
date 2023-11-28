package com.luggsoft.wci.core.commands.async

import com.luggsoft.wci.core.process.ProcessState
import java.util.UUID

data class AsyncCommandProcessDescriptor(
    val processId: UUID,
    val processState: ProcessState,
    val requestClass: Class<AsyncCommandRequest<*>>,
)
