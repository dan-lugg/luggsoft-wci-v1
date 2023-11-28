package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandResult
import com.luggsoft.wci.core.notifications.Notification

data class SelectNotificationsAwaitCommandResult(
    val notifications: List<Notification<*>>,
) : AwaitCommandResult
