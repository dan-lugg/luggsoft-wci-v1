package com.luggsoft.wci.core.commands.standard

import com.luggsoft.wci.core.commands.query.QueryCommandResult
import com.luggsoft.wci.core.process.ProcessDescriptor

data class SelectProcessDescriptorsQueryCommandResult(
    val processDescriptors: List<ProcessDescriptor>
) : QueryCommandResult
