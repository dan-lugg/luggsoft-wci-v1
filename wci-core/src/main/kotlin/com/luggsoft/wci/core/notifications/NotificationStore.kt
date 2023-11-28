package com.luggsoft.wci.core.notifications

import java.time.Instant
import java.util.UUID

interface NotificationStore : List<Notification<*>>
{
    fun createNotification(notification: Notification<*>)
    
    fun selectNotificationList(sourceId: UUID, startInstant: Instant, untilInstant: Instant = Instant.now()): List<Notification<*>>
}
