package com.luggsoft.wci.core.system

import java.lang.management.ManagementFactory
import java.time.Duration

class DefaultUptimeDurationProvider : UptimeDurationProvider
{
    private val runtimeMxBean = ManagementFactory.getRuntimeMXBean()
    
    override fun getUptimeDuration(): Duration
    {
        return this.runtimeMxBean.uptime.let(Duration::ofMillis)
    }
}
