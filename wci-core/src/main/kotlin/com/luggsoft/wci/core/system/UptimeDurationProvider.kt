package com.luggsoft.wci.core.system

import java.time.Duration

interface UptimeDurationProvider
{
    fun getUptimeDuration(): Duration
}
