package com.luggsoft.wci.core.tests

interface TestInvoker<TContext : TestContext>
{
    fun invokeTests(context: TContext): TestResult
}
