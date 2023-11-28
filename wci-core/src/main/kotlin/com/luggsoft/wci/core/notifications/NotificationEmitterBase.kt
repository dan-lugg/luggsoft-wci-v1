package com.luggsoft.wci.core.notifications

abstract class NotificationEmitterBase : NotificationEmitter
{
    protected abstract val notificationHandlers: List<NotificationHandler>
    
    override fun emitNotification(notification: Notification<*>)
    {
        for (notificationHandler in this.notificationHandlers)
        {
            try
            {
                notificationHandler.handleNotification(notification)
            } catch (exception: Exception)
            {
                TODO("Handle exception, $exception")
            }
        }
    }
}
