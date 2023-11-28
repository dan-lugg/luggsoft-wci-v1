package com.luggsoft.wci.core.commands

import com.luggsoft.wci.core.Transmittable

/**
 * Represents a request to be processed by its corresponding [CommandHandler], which produces a [CommandResult].
 *
 * @see CommandResult
 * @see CommandHandler
 */
interface CommandRequest<TCommandResult : CommandResult> : Transmittable
