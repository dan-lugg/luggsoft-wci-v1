package com.luggsoft.wci.module.exposed

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant
import java.util.UUID

object ProcessTable : Table(name = "process")
{
    val id: Column<UUID> = uuid(name = "id")
    
    val startedAt: Column<Instant> = timestamp(name = "started_at")
        .default(Instant.now())
    
    val abortedAt: Column<Instant?> = timestamp(name = "aborted_at")
        .nullable()
    
    val startedByUserId: Column<UUID> = (uuid(name = "started_by_user_id") references UserTable.id)
    
    val abortedByUserId: Column<UUID?> = (uuid(name = "aborted_by_user_id") references UserTable.id)
        .nullable()
    
    val name: Column<String> = varchar(name = "name", length = 255)
    
    val status: Column<String> = varchar(name = "status", length = 255)
    
    override val primaryKey: PrimaryKey = PrimaryKey(this.id)
}
