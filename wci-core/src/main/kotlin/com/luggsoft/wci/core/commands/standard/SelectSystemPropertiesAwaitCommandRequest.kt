package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.await.AwaitCommandRequest
import com.luggsoft.wci.core.web.WebInfo

@WebInfo(
    title = "Select System Properties",
    description = "Queries the available system properties.",
    isSystem = true,
)
class SelectSystemPropertiesAwaitCommandRequest : AwaitCommandRequest<SelectSystemPropertiesAwaitCommandResult>
