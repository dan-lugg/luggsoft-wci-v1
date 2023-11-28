package com.luggsoft.wci.core.notifications.standard

import com.luggsoft.wci.core.notifications.Notification
import com.luggsoft.wci.core.notifications.NotificationHandler
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory

class LogNotificationHandler(
    private val objectMapper: ObjectMapper,
) : NotificationHandler
{
    override fun handleNotification(notification: Notification<*>)
    {
        val notificationJson = this.objectMapper.writeValueAsString(notification)
        logger.info("{}", notificationJson)
    }
    
    companion object
    {
        private val logger = LoggerFactory.getLogger(LogNotificationHandler::class.java)
    }
}

