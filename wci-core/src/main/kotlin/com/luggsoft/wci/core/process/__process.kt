package com.luggsoft.wci.core.process

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationLevel
import com.luggsoft.wci.core.notifications.NotificationStore
import java.util.UUID
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.random.Random

fun createProcessContext(): ProcessContext = object : ProcessContext
{
    override val processId: UUID = UUID.randomUUID()
    
    override fun sendNotification(notification: Notification<*>)
    {
        println(" >>> $notification")
    }
}

fun main()
{
    val processManager = DefaultProcessManager(
        executor = Executors.newSingleThreadExecutor(),
    )
    
    processManager.startProcess(createProcessContext()) { processContext ->
        println("Start")
        repeat(10) {
            Thread.sleep(1_000)
            processContext.sendNotification(
                level = NotificationLevel.GENERAL,
                payload = "Hello!",
            )
        }
        
        println("Can we review this?")
        return@startProcess NotificationLevel.SUCCESS
    }
    
    processManager.startProcess(createProcessContext()) { processContext ->
        println("Start")
        repeat(10) {
            
            if (Random.nextBoolean())
            {
                return@startProcess NotificationLevel.FAILURE
            }
            
            Thread.sleep(1_000)
            processContext.sendNotification(
                level = NotificationLevel.GENERAL,
                payload = "Hello!",
            )
        }
        
        println("Can we review this?")
        return@startProcess NotificationLevel.SUCCESS
    }
    
    thread(start = true, isDaemon = false) {
        while (true)
        {
            processManager.printState()
            Thread.sleep(500)
        }
    }
    
    processManager.printState()
}

fun ProcessManager.printState()
{
    println("running=${this.runningProcessDescriptors.size}, pending=${this.pendingProcessDescriptors.size}, success=${this.successProcessDescriptors.size}, failure=${this.failureProcessDescriptors.size}, unknown=${this.unknownProcessDescriptors.size}")
}

///
///
///

class DefaultProcessContextFactory(
    private val notificationStore: NotificationStore,
) : ProcessContextFactory<DefaultProcessContext>
{
    override fun createProcessContext(processId: UUID): DefaultProcessContext
    {
        return DefaultProcessContext(
            processId = processId,
            notificationStore = this.notificationStore,
        )
    }
}

interface ProcessManagerFactory<TManager : ProcessManager>
{
    fun createProcessManager(): TManager
}

///
///
///

