package com.luggsoft.wci.module.spring.controllers

import com.luggsoft.wci.core.commands.CommandDispatcher
import com.luggsoft.wci.core.commands.CommandRequest
import com.luggsoft.wci.core.commands.CommandResult
import com.luggsoft.wci.core.commands.ErrorCommandResult
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/command"])
class CommandController(
    private val commandDispatcher: CommandDispatcher,
)
{
    @RequestMapping(path = [""], method = [RequestMethod.POST])
    fun handleCommandRequest(
        @RequestBody(required = true)
        commandRequest: CommandRequest<*>,
    ): ResponseEntity<CommandResult>
    {
        println(commandRequest)
        
        try
        {
            return this.commandDispatcher
                .dispatchCommand(commandRequest)
                .also(::println)
                .let(ResponseEntity.ok()::body)
        } catch (exception: Exception)
        {
            return ErrorCommandResult(exception)
                .also(::println)
                .let(ResponseEntity.internalServerError()::body)
        }
    }
    
    companion object
    {
        private val logger = LoggerFactory.getLogger(CommandController::class.java)
    }
}
