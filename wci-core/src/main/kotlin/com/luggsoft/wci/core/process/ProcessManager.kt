package com.luggsoft.wci.core.process

import java.util.UUID

interface ProcessManager : Map<UUID, ProcessDescriptor>
{
    val pendingProcessDescriptors: List<ProcessDescriptor>
    val runningProcessDescriptors: List<ProcessDescriptor>
    val successProcessDescriptors: List<ProcessDescriptor>
    val failureProcessDescriptors: List<ProcessDescriptor>
    val unknownProcessDescriptors: List<ProcessDescriptor>
    
    fun getAllProcessDescriptors(): List<ProcessDescriptor>
    
    fun startProcess(processContext: ProcessContext, processFunction: ProcessFunction): ProcessDescriptor
    
    fun abortProcess(processId: UUID)
}
