package com.luggsoft.wci.core.process

import java.util.UUID
import java.util.concurrent.Executor
import kotlin.concurrent.thread

abstract class ProcessManagerBase : ProcessManager
{
    protected abstract val executor: Executor
    
    protected abstract val processDescriptors: MutableList<ProcessDescriptor>
    
    override val pendingProcessDescriptors: List<ProcessDescriptor>
        get() = this.processDescriptors.filter { processDescriptor -> processDescriptor.processState == ProcessState.PENDING }
    
    override val runningProcessDescriptors: List<ProcessDescriptor>
        get() = this.processDescriptors.filter { processDescriptor -> processDescriptor.processState == ProcessState.RUNNING }
    
    override val successProcessDescriptors: List<ProcessDescriptor>
        get() = this.processDescriptors.filter { processDescriptor -> processDescriptor.processState == ProcessState.SUCCESS }
    
    override val failureProcessDescriptors: List<ProcessDescriptor>
        get() = this.processDescriptors.filter { processDescriptor -> processDescriptor.processState == ProcessState.FAILURE }
    
    override val unknownProcessDescriptors: List<ProcessDescriptor>
        get() = this.processDescriptors.filter { processDescriptor -> processDescriptor.processState == ProcessState.UNKNOWN }
    
    override fun getAllProcessDescriptors(): List<ProcessDescriptor> = this.processDescriptors
    
    override val entries: Set<Map.Entry<UUID, ProcessDescriptor>>
        get() = this.processDescriptors
            .associateBy(ProcessDescriptor::processId)
            .entries
    
    override val keys: Set<UUID>
        get() = this.processDescriptors
            .associateBy(ProcessDescriptor::processId)
            .keys
    
    override val size: Int
        get() = this.processDescriptors.size
    
    override val values: Collection<ProcessDescriptor>
        get() = this.processDescriptors
    
    final override fun startProcess(processContext: ProcessContext, processFunction: ProcessFunction): ProcessDescriptor
    {
        val processId = UUID.randomUUID()
        
        val processDescriptor = object : ProcessDescriptor
        {
            val thread: Thread = this.createThread(processFunction)
            
            override val processId: UUID = processId
            
            private var definiteProcessState: ProcessState? = null
            
            private val isPending: Boolean
                get() = false
            
            private val isRunning: Boolean
                get() = this.thread.isAlive
            
            private val isSuccess: Boolean
                get() = !(this.thread.isAlive || this.thread.isInterrupted)
            
            private val isFailure: Boolean
                get() = this.thread.isInterrupted
            
            override val processState: ProcessState
                get()
                {
                    val definiteProcessState = this.definiteProcessState
                    
                    if (definiteProcessState != null)
                    {
                        return definiteProcessState
                    }
                    
                    when
                    {
                        // TODO: Incorporate Thread.state into the classification
                        this.isPending -> ProcessState.PENDING
                        this.isRunning -> ProcessState.RUNNING
                        this.isFailure -> ProcessState.FAILURE
                        this.isSuccess -> ProcessState.SUCCESS
                    }
                    
                    return ProcessState.UNKNOWN
                }
            
            private fun createThread(function: ProcessFunction): Thread
            {
                val thread = thread(start = true, isDaemon = false) {
                    try
                    {
                        function(processContext)
                    }
                    catch (exception: Exception)
                    {
                        TODO("Failed, because: $exception")
                    }
                }
                
                thread.uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { _, throwable ->
                    this.definiteProcessState = ProcessState.FAILURE
                    TODO("Failed, because: $throwable")
                }
                
                return thread
            }
        }
        
        this.processDescriptors.add(processDescriptor)
        
        return processDescriptor
    }
    
    final override fun abortProcess(processId: UUID)
    {
        TODO("Not yet implemented")
    }
    
    override fun containsKey(key: UUID): Boolean
    {
        return this.keys.contains(key)
    }
    
    override fun containsValue(value: ProcessDescriptor): Boolean
    {
        return this.values.contains(value)
    }
    
    override fun get(key: UUID): ProcessDescriptor?
    {
        return this.processDescriptors.firstOrNull { processDescriptor -> processDescriptor.processId == key }
    }
    
    override fun isEmpty(): Boolean
    {
        return this.processDescriptors.isEmpty()
    }
}
