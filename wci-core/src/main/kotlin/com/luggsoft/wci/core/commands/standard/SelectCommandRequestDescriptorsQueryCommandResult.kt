package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandResult
import com.luggsoft.wci.core.commands.CommandRequestDescriptor

data class SelectCommandRequestDescriptorsQueryCommandResult(
    val commandRequestDescriptors: List<CommandRequestDescriptor>
) : QueryCommandResult
