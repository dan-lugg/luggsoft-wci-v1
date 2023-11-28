package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.await.AwaitCommandHandlerBase
import com.luggsoft.wci.core.notifications.NotificationStore

class SelectNotificationsAwaitCommandHandler(
    private val notificationStore: NotificationStore,
) : AwaitCommandHandlerBase<SelectNotificationsAwaitCommandRequest, SelectNotificationsAwaitCommandResult>()
{
    override fun handle(request: SelectNotificationsAwaitCommandRequest, context: CommandContext): SelectNotificationsAwaitCommandResult
    {
        return SelectNotificationsAwaitCommandResult(
            notifications = this.notificationStore.selectNotificationList(
                sourceId = request.sourceId,
                startInstant = request.startInstant,
                untilInstant = request.untilInstant,
            ),
        )
    }
}
