package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.commands.async.AsyncCommandRequest
import com.luggsoft.wci.core.commands.query.QueryCommandRequest
import com.luggsoft.wci.core.search.Criteria
import com.luggsoft.wci.core.util.ClassScanner
import com.luggsoft.wci.core.util.scanSubClasses
import com.fasterxml.jackson.databind.JsonNode
import com.github.victools.jsonschema.generator.SchemaGenerator
import java.lang.reflect.Modifier

class DefaultCommandRequestDescriptorProvider(
    private val classScanner: ClassScanner,
    private val schemaGenerator: SchemaGenerator,
) : CommandRequestDescriptorProvider
{
    @Suppress("UNCHECKED_CAST")
    @Throws(NotImplementedError::class)
    override fun getCommandRequestDescriptors(): List<CommandRequestDescriptor>
    {
        try
        {
            return this.classScanner.scanSubClasses<CommandRequest<*>>()
                .filter { commandRequestClass -> !Modifier.isAbstract(commandRequestClass.modifiers) }
                .filter { commandRequestClass -> !Modifier.isInterface(commandRequestClass.modifiers) }
                .map { commandRequestClass -> commandRequestClass as Class<CommandRequest<*>> }
                .map { commandRequestClass ->
                    val schemaJsonNode = this.schemaGenerator.generateSchema(commandRequestClass) as JsonNode
                    return@map CommandRequestDescriptor(
                        title = schemaJsonNode.get("title").asText(commandRequestClass.name.substringAfterLast('.')),
                        description = schemaJsonNode.get("description").asText(),
                        commandRequestClass = commandRequestClass,
                        commandRequestSchema = schemaJsonNode,
                        commandExecutionType = this.getCommandExecutionType(commandRequestClass),
                        commandDefinitionOrigin = this.getCommandDefinitionOrigin(commandRequestClass),
                    )
                }
        } catch (exception: Exception)
        {
            TODO("Handle exception, $exception")
        }
    }
    
    override fun findFirst(criteria: Criteria): CommandRequestDescriptor
    {
        TODO("Not yet implemented")
    }
    
    override fun findEvery(criteria: Criteria): List<CommandRequestDescriptor>
    {
        return this.getCommandRequestDescriptors()
    }
    
    private fun getCommandExecutionType(commandRequestClass: Class<*>): CommandExecutionType = when
    {
        AsyncCommandRequest::class.java.isAssignableFrom(commandRequestClass) -> CommandExecutionType.ASYNC
        QueryCommandRequest::class.java.isAssignableFrom(commandRequestClass) -> CommandExecutionType.AWAIT
        else -> TODO("Unusable assignment type, for $commandRequestClass")
    }
    
    private fun getCommandDefinitionOrigin(commandRequestClass: Class<*>): CommandDefinitionOrigin = when
    {
        commandRequestClass.packageName.startsWith(SYSTEM_PACKAGE_NAME) -> CommandDefinitionOrigin.SYSTEM
        else -> CommandDefinitionOrigin.CLIENT
    }
    
    private companion object
    {
        const val SYSTEM_PACKAGE_NAME = "com.luggsoft.wci.core.commands.standard"
    }
}
