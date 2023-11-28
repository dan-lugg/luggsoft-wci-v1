package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandRequest
import com.luggsoft.wci.core.web.WebInfo
import java.time.Instant
import java.util.UUID

@WebInfo(
    title = "Select Notifications",
    description = "Queries for the notifications from a particular source channel, during a particular time window.",
    isSystem = true,
)
data class SelectNotificationsAwaitCommandRequest(
    @field:WebInfo(
        title = "Source ID",
        description = "The notification source channel ID.",
    )
    val sourceId: UUID,
    
    @field:WebInfo(
        title = "Start Date/Time",
        description = "The start date/time for the notification source channel query."
    )
    val startInstant: Instant,
    
    @field:WebInfo(
        title = "Until Date/Time",
        description = "The end date/time for the notification source channel query."
    )
    val untilInstant: Instant = Instant.now(),
) : AwaitCommandRequest<SelectNotificationsAwaitCommandResult>
