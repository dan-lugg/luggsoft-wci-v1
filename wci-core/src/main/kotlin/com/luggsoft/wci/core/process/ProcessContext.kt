package com.luggsoft.wci.core.process

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationLevel
import java.time.Instant
import java.util.UUID

interface ProcessContext
{
    val processId: UUID
    
    fun sendNotification(notification: Notification<*>)
    
    fun <TPayload> sendNotification(level: NotificationLevel, payload: TPayload)
    {
        this.sendNotification(
            notification = Notification(
                at = Instant.now(),
                level = level,
                sourceId = this.processId,
                payload = payload,
            ),
        )
    }
}
