package com.luggsoft.wci.core.notifications

interface NotificationEmitter
{
    fun emitNotification(notification: Notification<*>)
}
