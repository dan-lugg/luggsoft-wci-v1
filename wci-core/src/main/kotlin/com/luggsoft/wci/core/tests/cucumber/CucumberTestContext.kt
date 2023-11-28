package com.luggsoft.wci.core.tests.cucumber

import com.luggsoft.wci.core.tests.TestContext

class CucumberTestContext(
    override val includeHtmlReport: Boolean,
    override val includeJsonReport: Boolean,
) : TestContext
