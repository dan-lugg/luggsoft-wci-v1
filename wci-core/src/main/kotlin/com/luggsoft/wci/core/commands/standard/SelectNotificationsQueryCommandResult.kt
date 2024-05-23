package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandResult
import com.luggsoft.wci.core.notifications.Notification

data class SelectNotificationsQueryCommandResult(
    val notifications: List<Notification<*>>,
) : QueryCommandResult
