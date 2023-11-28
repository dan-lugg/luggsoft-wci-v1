package com.luggsoft.wci.core

class NotImplementedException : Exception
{
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}
