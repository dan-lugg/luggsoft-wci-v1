package com.luggsoft.wci.module.exposed

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.UUID

object UserTable : Table(name = "user")
{
    val id: Column<UUID> = uuid(name = "id")
    
    val key: Column<String> = varchar(name = "key", length = 255)
    
    val name: Column<String> = varchar(name = "name", length = 50)
    
    override val primaryKey: PrimaryKey = PrimaryKey(this.id)
}
