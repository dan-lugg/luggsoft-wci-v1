package com.luggsoft.wci.core.commands

interface CommandContextFactory
{
    fun createContext(): CommandContext
}
