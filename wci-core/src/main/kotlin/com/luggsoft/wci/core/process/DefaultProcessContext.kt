package com.luggsoft.wci.core.process

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationStore
import java.util.UUID

class DefaultProcessContext(
    override val processId: UUID,
    private val notificationStore: NotificationStore,
) : ProcessContext
{
    override fun sendNotification(notification: Notification<*>)
    {
        this.notificationStore.createNotification(notification)
    }
}
