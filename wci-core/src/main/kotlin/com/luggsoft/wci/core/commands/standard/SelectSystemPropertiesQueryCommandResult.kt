package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandResult
import com.luggsoft.wci.core.system.Version
import java.time.Duration

data class SelectSystemPropertiesQueryCommandResult(
    val instanceName: String,
    val serverVersion: Version,
    val uptimeDuration: Duration,
) : QueryCommandResult
