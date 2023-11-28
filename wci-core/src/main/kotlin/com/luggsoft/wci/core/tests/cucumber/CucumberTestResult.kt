package com.luggsoft.wci.core.tests.cucumber

import com.luggsoft.wci.core.tests.TestResult

data class CucumberTestResult(
    override val code: Int,
) : TestResult
