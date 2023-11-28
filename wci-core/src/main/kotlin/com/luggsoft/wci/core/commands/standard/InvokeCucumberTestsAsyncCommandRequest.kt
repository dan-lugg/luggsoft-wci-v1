package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.async.AsyncCommandRequest
import com.luggsoft.wci.core.web.WebInfo
import jakarta.validation.constraints.NotEmpty

@WebInfo(
    title = "Invoke Cucumber Tests",
    description = "Executes the cucumber tests associated with this ATC project.",
    isSystem = true,
)
data class InvokeCucumberTestsAsyncCommandRequest(
    @field:NotEmpty
    @field:WebInfo(
        title = "Example Test Name",
        description = "This is the example test name."
    )
    val testName: String,
    
    @field:WebInfo(
        title = "Include HTML Report",
        description = "Whether or not an HTML test report will be generated and linked for the included tests.",
    )
    val htmlReport: Boolean = true,
    
    @field:WebInfo(
        title = "Include JSON Report",
        description = "Whether or not a JSON test report will be generated and linked for the included tests.",
    )
    val jsonReport: Boolean = true,
    
    @field:NotEmpty
    @field:WebInfo(
        title = "Tag Filters",
        description = "A list of tags to include in the test execution. If empty, all tests will be executed.",
    )
    val filterTags: List<@NotEmpty @WebInfo(
        title = "Tag Filter",
        description = "A tag to include in test execution.",
    ) String> = emptyList(),
) : AsyncCommandRequest<InvokeCucumberTestsAsyncCommandResult>

