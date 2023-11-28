package com.luggsoft.wci.core.process

import kotlin.concurrent.thread

class Process(
    function: () -> Unit,
) : Runnable
{
    private val thread: Thread
    
    init
    {
        this.thread = thread {
            function()
        }
    }
    
    override fun run()
    {
        this.thread.start()
    }
}
