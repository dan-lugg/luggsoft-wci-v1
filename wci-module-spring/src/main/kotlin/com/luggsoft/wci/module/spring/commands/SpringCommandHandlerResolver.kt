package com.luggsoft.wci.module.spring.commands

import com.luggsoft.wci.core.NotImplementedException
import com.luggsoft.wci.core.commands.CommandHandler
import com.luggsoft.wci.core.commands.CommandHandlerResolverBase
import com.luggsoft.wci.core.commands.CommandRequest
import com.luggsoft.wci.core.util.ClassScanner
import com.luggsoft.wci.core.util.scanSubClasses
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import java.lang.reflect.Modifier

class SpringCommandHandlerResolver(
    private val classScanner: ClassScanner,
    private val autowireCapableBeanFactory: AutowireCapableBeanFactory,
) : CommandHandlerResolverBase()
{
    private val commandHandlers: List<CommandHandler<CommandRequest<*>, *>>
    
    init
    {
        try
        {
            this.commandHandlers = this.classScanner.scanSubClasses<CommandHandler<CommandRequest<*>, *>>()
                .filter { commandHandlerClass -> !Modifier.isAbstract(commandHandlerClass.modifiers) }
                .filter { commandHandlerClass -> !Modifier.isInterface(commandHandlerClass.modifiers) }
                .map { commandExecutorClass -> this.autowireCapableBeanFactory.createBean(commandExecutorClass) }
                .toList()
        } catch (exception: Exception)
        {
            TODO("Handle exception, $exception")
        }
    }
    
    override fun createCommandHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
    {
        try
        {
            return this.commandHandlers.single { commandExecutor -> commandExecutor.requestClass == requestClass }
        } catch (exception: Exception)
        {
            throw NotImplementedException("Handle exception, $exception", exception)
        }
    }
}
