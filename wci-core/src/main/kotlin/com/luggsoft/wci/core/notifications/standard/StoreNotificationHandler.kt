package com.luggsoft.wci.core.notifications.standard

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationHandler
import com.luggsoft.wci.core.notifications.NotificationStore

class StoreNotificationHandler(
    private val notificationStore: NotificationStore,
) : NotificationHandler
{
    override fun handleNotification(notification: Notification<*>)
    {
        try
        {
            this.notificationStore.createNotification(notification)
        } catch (exception: Exception)
        {
            TODO("Unhandled exception, $exception")
        }
    }
}
