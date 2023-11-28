package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.notifications.NotificationEmitter

interface CommandContext
{
    val notificationEmitter: NotificationEmitter
    
    fun <TValue> getValue(name: String, valueClass: Class<TValue>): TValue?
}

