package com.luggsoft.wci.core._vx

import com.luggsoft.wci.core.commands.async.AsyncCommandRequest
import java.time.Instant
import java.util.UUID

interface User
{
    val id: UUID
}

interface Process
{
    val id: UUID
    val startedByUserId: UUID
    val abortedByUserId: UUID?
}

interface ProcessMessage
{
    val id: UUID
    val processId: UUID
}

///
///
///

interface Event<TEventData : EventData>
{
    val id: UUID
    val at: Instant
    val eventData: TEventData
}

interface EventData

///
///
///

data class StartAsyncCommandProcessEvent(
    override val id: UUID,
    override val at: Instant,
    override val eventData: StartAsyncCommandProcessEventData,
) : Event<StartAsyncCommandProcessEventData>

data class StartAsyncCommandProcessEventData(
    val command: AsyncCommandRequest<*>,
    val startedByUserId: UUID,
) : EventData

data class AbortAsyncCommandProcessEvent(
    override val id: UUID,
    override val at: Instant,
    override val eventData: AbortAsyncCommandProcessEventData,
) : Event<AbortAsyncCommandProcessEventData>

data class AbortAsyncCommandProcessEventData(
    val processId: UUID,
    val abortedByUserId: UUID,
) : EventData

///
///
///

interface EventProducer
{
    fun produceEvent(event: Event<*>)
}

interface EventConsumer
{
    fun consumeEvent(event: Event<*>)
}

