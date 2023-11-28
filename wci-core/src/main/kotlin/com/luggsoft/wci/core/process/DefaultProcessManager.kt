package com.luggsoft.wci.core.process

import java.util.concurrent.Executor

class DefaultProcessManager(
    override val executor: Executor,
    override val processDescriptors: MutableList<ProcessDescriptor> = mutableListOf(),
) : ProcessManagerBase()
