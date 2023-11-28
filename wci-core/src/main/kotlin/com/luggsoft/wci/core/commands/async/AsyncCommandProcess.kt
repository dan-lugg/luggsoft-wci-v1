package com.luggsoft.wci.core.commands.async

import java.util.UUID

data class AsyncCommandProcess(
    val processId: UUID,
    val requestClass: Class<AsyncCommandRequest<*>>,
)
