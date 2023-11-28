package com.luggsoft.wci.core

import com.luggsoft.wci.core.commands.standard.InvokeCucumberTestsAsyncCommandRequest
import com.luggsoft.wci.core.web.WebInfo
import com.github.victools.jsonschema.generator.MemberScope
import com.github.victools.jsonschema.generator.Option
import com.github.victools.jsonschema.generator.SchemaGenerator
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder
import com.github.victools.jsonschema.generator.SchemaVersion

fun main()
{
    val schemaGeneratorConfig = SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12)
        .with(Option.ADDITIONAL_FIXED_TYPES)
        .with(Option.DEFINITIONS_FOR_ALL_OBJECTS)
        .with(Option.EXTRA_OPEN_API_FORMAT_VALUES)
        .without(Option.FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT)
        .without(Option.GETTER_METHODS)
        .without(Option.NONPUBLIC_STATIC_FIELDS)
        .without(Option.NONSTATIC_NONVOID_NONGETTER_METHODS)
        .without(Option.VOID_METHODS)
        .also { schemaGeneratorConfigBuilder ->
            schemaGeneratorConfigBuilder.forTypesInGeneral()
                .withTitleResolver title@{ scope ->
                    return@title when (scope)
                    {
                        is MemberScope<*, *> -> scope.getAnnotation(WebInfo::class.java)?.title
                            ?: "T:${scope.name}"
                        
                        else -> scope.type.erasedType.getAnnotation(WebInfo::class.java)?.title
                            ?: "T:${scope.type.typeName}"
                    }
                }
                .withDescriptionResolver title@{ scope ->
                    return@title when (scope)
                    {
                        is MemberScope<*, *> -> scope.getAnnotation(WebInfo::class.java)?.description
                            ?: "D:${scope.name}"
                        
                        else -> scope.type.erasedType.getAnnotation(WebInfo::class.java)?.description
                            ?: "D:${scope.type.typeName}"
                    }
                }
            
            /*
            schemaGeneratorConfigBuilder.forFields()
                // TODO: Figure out how to find and use the GuiDescriptor::title property
                .withTitleResolver title@{ fieldScope ->
                    println("fieldScope:title=$fieldScope")
                    return@title fieldScope.getAnnotation(WebInfo::class.java)?.title ?: "T:${fieldScope.name}"
                }
                .withDescriptionResolver description@{ fieldScope ->
                    println("fieldScope:description=$fieldScope")
                    return@description fieldScope.getAnnotation(WebInfo::class.java)?.description ?: "D:${fieldScope.name}"
                }
            */
            
        }
        .build()
    
    val schemaGenerator = SchemaGenerator(schemaGeneratorConfig)
    
    val schema = schemaGenerator.generateSchema(InvokeCucumberTestsAsyncCommandRequest::class.java)
    
    println(schema.toPrettyString())
}
