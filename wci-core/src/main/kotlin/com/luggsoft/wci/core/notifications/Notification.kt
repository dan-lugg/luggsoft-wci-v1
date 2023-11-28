package com.luggsoft.wci.core.notifications

import com.luggsoft.wci.core.Transmittable
import java.time.Instant
import java.util.UUID

data class Notification<TPayload>(
    val at: Instant,
    val level: NotificationLevel,
    val sourceId: UUID,
    val payload: TPayload,
) : Transmittable

