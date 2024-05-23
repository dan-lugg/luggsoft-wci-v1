package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandRequest
import com.luggsoft.wci.core.web.WebInfo

@WebInfo(
    title = "Select Command Request Descriptors",
    description = "Queries for command request descriptors available.",
    isSystem = true,
)
class SelectCommandRequestDescriptorsQueryCommandRequest : QueryCommandRequest<SelectCommandRequestDescriptorsQueryCommandResult>
