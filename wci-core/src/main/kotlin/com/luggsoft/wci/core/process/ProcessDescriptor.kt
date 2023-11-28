package com.luggsoft.wci.core.process

import com.luggsoft.wci.core.Transmittable
import java.util.UUID

interface ProcessDescriptor : Transmittable
{
    val processId: UUID
    val processState: ProcessState
}
