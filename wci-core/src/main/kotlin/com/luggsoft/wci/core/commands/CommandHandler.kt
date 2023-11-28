package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.util.typeParameterClasses

/**
 * Responsible for handling a provided [TRequest], derived from [CommandRequest], and producing a [TResult], derived from [CommandResult].
 *
 * @see AsyncCommandHandlerBase
 * @see AwaitCommandHandlerBase
 */
interface CommandHandler<TRequest : CommandRequest<TResult>, TResult : CommandResult>
{
    /**
     * The [TResult], derived from [CommandResult], type this [CommandHandler] produces as an output.
     *
     * @see CommandResult
     * @see AsyncCommandResult
     * @see AwaitCommandResult
     */
    @Suppress("UNCHECKED_CAST")
    val resultClass: Class<TResult>
        @Throws(RuntimeException::class)
        get()
        {
            try
            {
                return this::class.java.typeParameterClasses[1] as? Class<TResult>
                    ?: throw ClassCastException("Can't convert $this::${this::resultClass.name} to ${CommandResult::class.java}")
            } catch (exception: Exception)
            {
                throw RuntimeException("Failed to resolve ${this}::${this::resultClass.name}; type not bound or convertible to ${CommandResult::class.java}", exception)
            }
        }
    
    /**
     * The [TRequest] type, derived from [CommandRequest], this [CommandHandler] accepts as an input.
     *
     * @see CommandRequest
     * @see AsyncCommandRequest
     * @see AwaitCommandRequest
     */
    @Suppress("UNCHECKED_CAST")
    val requestClass: Class<TRequest>
        @Throws(RuntimeException::class) get()
        {
            try
            {
                return this::class.java.typeParameterClasses[0] as? Class<TRequest>
                    ?: throw ClassCastException("Can't convert $this::${this::requestClass.name} to ${CommandRequest::class.java}")
            } catch (exception: Exception)
            {
                throw RuntimeException("Failed to resolve ${this}::${this::requestClass.name}; type not bound or convertible to ${CommandRequest::class.java}", exception)
            }
        }
    
    fun handle(request: TRequest, context: CommandContext): TResult
}
