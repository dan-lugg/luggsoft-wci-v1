package com.luggsoft.wci.core.process

interface ProcessDescriptorProvider
{
    fun getProcessDescriptors(): List<ProcessDescriptor>
    
    fun getProcessDescriptors(processState: ProcessState): List<ProcessDescriptor>
}
