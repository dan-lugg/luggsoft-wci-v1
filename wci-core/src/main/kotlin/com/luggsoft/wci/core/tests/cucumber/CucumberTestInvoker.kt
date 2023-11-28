package com.luggsoft.wci.core.tests.cucumber

import com.luggsoft.wci.core.tests.TestContext
import com.luggsoft.wci.core.tests.TestInvoker
import com.luggsoft.wci.core.tests.TestResult
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.core.eventbus.EventBus
import io.cucumber.core.options.CommandlineOptionsParser
import io.cucumber.core.options.CucumberProperties
import io.cucumber.core.options.CucumberPropertiesParser
import io.cucumber.core.options.RuntimeOptions
import io.cucumber.core.plugin.HtmlFormatter
import io.cucumber.core.plugin.JsonFormatter
import io.cucumber.core.runtime.Runtime
import io.cucumber.core.runtime.TimeServiceEventBus
import io.cucumber.plugin.Plugin
import io.cucumber.plugin.event.Event
import org.jetbrains.kotlin.utils.addToStdlib.ifTrue
import java.io.ByteArrayOutputStream
import java.time.Clock
import java.util.UUID

class CucumberTestInvoker(
    private val classLoader: ClassLoader,
    private val objectMapper: ObjectMapper,
) : TestInvoker<CucumberTestContext>
{
    override fun invokeTests(context: CucumberTestContext): TestResult
    {
        val plugins = this.createPluginArray(context)
        val eventBus = this.createEventBus(context)
        val commandlineOptionsParser = CommandlineOptionsParser(System.out)
        
        val runtimeOptions = commandlineOptionsParser
            .parse(*emptyArray())
            .addDefaultGlueIfAbsent()
            .addDefaultFeaturePathIfAbsent()
            .addDefaultSummaryPrinterIfNotDisabled()
            .enablePublishPlugin()
            .build(this.getRuntimeOptions())
        
        val runtime = Runtime.builder()
            .withAdditionalPlugins(*plugins)
            .withEventBus(eventBus)
            .withRuntimeOptions(runtimeOptions)
            .withClassLoader { this.classLoader }
            .build()
        
        runtime.run()
        
        return CucumberTestResult(
            code = runtime.exitStatus().toInt(),
        )
    }
    
    private fun createPluginArray(context: TestContext): Array<Plugin>
    {
        val plugins = mutableListOf<Plugin>()
        
        context.includeHtmlReport.ifTrue {
            val htmlOutputStream = ByteArrayOutputStream()
            plugins += HtmlFormatter(htmlOutputStream)
        }
        
        context.includeJsonReport.ifTrue {
            val jsonOutputStream = ByteArrayOutputStream()
            plugins += JsonFormatter(jsonOutputStream)
        }
        
        return plugins.toTypedArray()
    }
    
    private fun createEventBus(context: TestContext): EventBus
    {
        val clock = Clock.systemDefaultZone()
        val eventBus = TimeServiceEventBus(clock, UUID::randomUUID)
        
        eventBus.registerHandlerFor(Event::class.java) { event ->
            /*
            context.activityEmitter.emitActivity(
                activity = Activity(
                    at = Instant.now(),
                    payload = this.objectMapper.convertValue(event, JsonNode::class.java),
                )
            )
            */
            // TODO: Replace with NotificationEmitter
        }
        
        return eventBus
    }
    
    private fun getRuntimeOptions(): RuntimeOptions
    {
        val propertiesRuntimeOptions = CucumberPropertiesParser()
            .parse(CucumberProperties.fromPropertiesFile())
            .build()
        
        val environmentRuntimeOptions = CucumberPropertiesParser()
            .parse(CucumberProperties.fromEnvironment())
            .build(propertiesRuntimeOptions)
        
        return CucumberPropertiesParser()
            .parse(CucumberProperties.fromSystemProperties())
            .build(environmentRuntimeOptions)
    }
}
