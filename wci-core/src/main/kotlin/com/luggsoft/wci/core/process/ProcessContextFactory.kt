package com.luggsoft.wci.core.process

import java.util.UUID

interface ProcessContextFactory<TContext : ProcessContext>
{
    fun createProcessContext(processId: UUID): TContext
}
