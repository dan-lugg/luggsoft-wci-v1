package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandRequest
import com.luggsoft.wci.core.web.WebInfo

@WebInfo(
    title = "Select System Properties",
    description = "Queries the available system properties.",
    isSystem = true,
)
class SelectSystemPropertiesQueryCommandRequest : QueryCommandRequest<SelectSystemPropertiesQueryCommandResult>
