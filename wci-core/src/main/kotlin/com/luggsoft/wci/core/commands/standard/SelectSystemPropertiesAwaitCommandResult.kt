package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandResult
import com.luggsoft.wci.core.system.Version
import java.time.Duration

data class SelectSystemPropertiesAwaitCommandResult(
    val instanceName: String,
    val serverVersion: Version,
    val uptimeDuration: Duration,
) : AwaitCommandResult
