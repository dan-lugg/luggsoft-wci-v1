package com.luggsoft.wci.core.jsonschema

import com.github.victools.jsonschema.generator.Module
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder

class WciModule(
    private val options: Set<WciOption> = emptySet(),
) : Module
{
    override fun applyToConfigBuilder(builder: SchemaGeneratorConfigBuilder)
    {
        val objectMapper = builder.objectMapper
        
        val fieldConfigPart = builder.forFields()
        val methodConfigPart = builder.forMethods()
        
    }
}

enum class WciOption

fun example() {
    val module = WciModule()
}
