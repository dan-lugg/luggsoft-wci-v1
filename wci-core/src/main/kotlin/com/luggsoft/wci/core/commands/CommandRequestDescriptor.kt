package com.luggsoft.wci.core.commands

import com.fasterxml.jackson.databind.JsonNode

data class CommandRequestDescriptor(
    val title: String,
    val description: String,
    val commandRequestClass: Class<*>,
    val commandRequestSchema: JsonNode,
    val commandExecutionType: CommandExecutionType,
    val commandDefinitionOrigin: CommandDefinitionOrigin
)
