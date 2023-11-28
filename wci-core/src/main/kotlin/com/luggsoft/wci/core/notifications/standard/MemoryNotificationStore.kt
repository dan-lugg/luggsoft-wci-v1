package com.luggsoft.wci.core.notifications.standard

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationStore
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.UUID

class MemoryNotificationStore : NotificationStore
{
    private val notifications = mutableListOf<Notification<*>>()
    
    override fun createNotification(notification: Notification<*>)
    {
        try
        {
            logger.info("Adding notification, $notification")
            this.notifications.add(notification)
        }
        catch (exception: Exception)
        {
            TODO("Unhandled exception, $exception")
        }
    }
    
    override fun selectNotificationList(sourceId: UUID, startInstant: Instant, untilInstant: Instant): List<Notification<*>>
    {
        return this.notifications
            .filter { notification -> notification.sourceId == sourceId }
            .filter { notification -> notification.at.isAfter(startInstant) }
            .filter { notification -> notification.at.isBefore(untilInstant) }
            .sortedBy { notification -> notification.at }
    }
    
    override val size: Int
        get() = this.notifications.size
    
    override fun contains(element: Notification<*>): Boolean
    {
        return this.notifications.contains(element)
    }
    
    override fun containsAll(elements: Collection<Notification<*>>): Boolean
    {
        return this.notifications.containsAll(elements)
    }
    
    override fun get(index: Int): Notification<*>
    {
        return this.notifications[index]
    }
    
    override fun indexOf(element: Notification<*>): Int
    {
        return this.notifications.indexOf(element)
    }
    
    override fun isEmpty(): Boolean
    {
        return this.notifications.isEmpty()
    }
    
    override fun iterator(): Iterator<Notification<*>>
    {
        return this.notifications.iterator()
    }
    
    override fun lastIndexOf(element: Notification<*>): Int
    {
        return this.notifications.lastIndexOf(element)
    }
    
    override fun listIterator(): ListIterator<Notification<*>>
    {
        return this.notifications.listIterator()
    }
    
    override fun listIterator(index: Int): ListIterator<Notification<*>>
    {
        return this.notifications.listIterator(index)
    }
    
    override fun subList(fromIndex: Int, toIndex: Int): List<Notification<*>>
    {
        return this.notifications.subList(fromIndex, toIndex)
    }
    
    companion object
    {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}
