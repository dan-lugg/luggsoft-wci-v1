package com.luggsoft.wci.module.exposed

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.json.json
import java.time.Instant
import java.util.UUID

object ProcessLogTable : Table(name = "process_log")
{
    val id: Column<UUID> = uuid(name = "id")
    
    val loggedAt: Column<Instant> = timestamp(name = "logged_at")
        .default(Instant.now())
    
    val processId: Column<UUID> = (uuid(name = "process_id") references ProcessTable.id)
    
    val content: Column<ProcessLogMessage> = json(
        name = "message",
        serialize = objectMapper::writeValueAsString,
        deserialize = { json -> objectMapper.readValue(json, ProcessLogMessage::class.java) })
    
    override val primaryKey: PrimaryKey = PrimaryKey(this.id)
}
