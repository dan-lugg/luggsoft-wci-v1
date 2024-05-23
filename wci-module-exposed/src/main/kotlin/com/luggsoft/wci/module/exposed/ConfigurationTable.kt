package com.luggsoft.wci.module.exposed

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ConfigurationTable : Table(name = "configuration")
{
    val key: Column<String> = varchar(name = "key", length = 255)
    
    val value: Column<String> = varchar(name = "value", length = 255)
}
