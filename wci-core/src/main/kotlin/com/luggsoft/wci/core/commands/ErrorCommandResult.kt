package com.luggsoft.wci.core.commands

data class ErrorCommandResult(
    val exception: Exception,
) : CommandResult
