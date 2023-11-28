package com.luggsoft.wci.core.process

class DefaultProcessDescriptorProvider(
    private val processManager: ProcessManager,
) : ProcessDescriptorProviderBase()
{
    override fun getProcessDescriptors(): List<ProcessDescriptor>
    {
        return this.processManager.getAllProcessDescriptors()
    }
    
    override fun getProcessDescriptors(processState: ProcessState): List<ProcessDescriptor>
    {
        return when (processState)
        {
            ProcessState.PENDING -> this.processManager.pendingProcessDescriptors
            ProcessState.RUNNING -> this.processManager.runningProcessDescriptors
            ProcessState.SUCCESS -> this.processManager.successProcessDescriptors
            ProcessState.FAILURE -> this.processManager.failureProcessDescriptors
            ProcessState.UNKNOWN -> this.processManager.unknownProcessDescriptors
        }
    }
}
