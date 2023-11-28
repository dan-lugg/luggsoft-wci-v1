package com.luggsoft.wci.core.notifications

interface NotificationHandler
{
    fun handleNotification(notification: Notification<*>)
}
