package com.luggsoft.wci.core.process

import com.luggsoft.wci.core.notifications.NotificationLevel

fun interface ProcessFunction : (ProcessContext) -> NotificationLevel
