package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.query.QueryCommandHandlerBase
import com.luggsoft.wci.core.notifications.NotificationStore

class SelectNotificationsQueryCommandHandler(
    private val notificationStore: NotificationStore,
) : QueryCommandHandlerBase<SelectNotificationsQueryCommandRequest, SelectNotificationsQueryCommandResult>()
{
    override fun handle(request: SelectNotificationsQueryCommandRequest, context: CommandContext): SelectNotificationsQueryCommandResult
    {
        return SelectNotificationsQueryCommandResult(
            notifications = this.notificationStore.selectNotificationList(
                sourceId = request.sourceId,
                startInstant = request.startInstant,
                untilInstant = request.untilInstant,
            ),
        )
    }
}
