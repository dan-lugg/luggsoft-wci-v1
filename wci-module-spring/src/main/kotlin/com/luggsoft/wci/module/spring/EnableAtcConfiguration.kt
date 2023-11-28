package com.luggsoft.wci.module.spring

import com.luggsoft.wci.core.commands.CommandContext
import com.luggsoft.wci.core.commands.CommandContextFactory
import com.luggsoft.wci.core.commands.CommandDispatcher
import com.luggsoft.wci.core.commands.CommandDispatcherBase
import com.luggsoft.wci.core.commands.CommandHandler
import com.luggsoft.wci.core.commands.CommandHandlerResolver
import com.luggsoft.wci.core.commands.CommandRequest
import com.luggsoft.wci.core.commands.CommandRequestDescriptorProvider
import com.luggsoft.wci.core.commands.DefaultCommandRequestDescriptorProvider
import com.luggsoft.wci.core.notifications.NotificationEmitter
import com.luggsoft.wci.core.notifications.NotificationEmitterBase
import com.luggsoft.wci.core.notifications.NotificationHandler
import com.luggsoft.wci.core.notifications.NotificationStore
import com.luggsoft.wci.core.notifications.standard.MemoryNotificationStore
import com.luggsoft.wci.core.process.DefaultProcessDescriptorProvider
import com.luggsoft.wci.core.process.DefaultProcessManager
import com.luggsoft.wci.core.process.ProcessDescriptorProvider
import com.luggsoft.wci.core.process.ProcessManager
import com.luggsoft.wci.core.system.DefaultInstanceNameProvider
import com.luggsoft.wci.core.system.DefaultServerVersionProvider
import com.luggsoft.wci.core.system.DefaultUptimeDurationProvider
import com.luggsoft.wci.core.system.InstanceNameProvider
import com.luggsoft.wci.core.system.ServerVersionProvider
import com.luggsoft.wci.core.system.UptimeDurationProvider
import com.luggsoft.wci.core.util.ClassScanner
import com.luggsoft.wci.core.util.ReflectionsClassScanner
import com.luggsoft.wci.core.web.WebInfo
import com.luggsoft.wci.module.spring.commands.SpringCommandHandlerResolver
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.victools.jsonschema.generator.MemberScope
import com.github.victools.jsonschema.generator.Option
import com.github.victools.jsonschema.generator.SchemaGenerator
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder
import com.github.victools.jsonschema.generator.SchemaVersion
import org.reflections.Reflections
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanNameGenerator
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.AnnotationBeanNameGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar
import org.springframework.context.annotation.Scope
import org.springframework.core.annotation.AnnotationAttributes
import org.springframework.core.env.Environment
import org.springframework.core.type.AnnotationMetadata
import org.springframework.core.type.StandardAnnotationMetadata
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.util.Collections
import java.util.concurrent.Executors

@Configuration
@EnableWebMvc
// TODO: Figure out fucking WebSockets
// @EnableWebSocketMessageBroker
@ComponentScan(basePackages = ["com.luggsoft.wci.module.spring"])
@Import(
    WebMvcAtcConfiguration::class,
    // TODO: Figure out fucking WebSockets
    // WebSocketAtcConfiguration::class
)
class EnableAtcConfiguration : ImportBeanDefinitionRegistrar, EnvironmentAware
{
    private lateinit var environment: Environment
    
    // <editor-fold desc="Beans from API">
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun instanceNameProvider(): InstanceNameProvider
    {
        return DefaultInstanceNameProvider()
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun serverVersionProvider(): ServerVersionProvider
    {
        return DefaultServerVersionProvider()
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun uptimeDurationProvider(): UptimeDurationProvider
    {
        return DefaultUptimeDurationProvider()
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun classScanner(): ClassScanner
    {
        return ReflectionsClassScanner(
            reflections = Reflections("com.luggsoft.wci"),
        )
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun commandDispatcher(
        commandHandlerResolver: CommandHandlerResolver,
    ): CommandDispatcher
    {
        return object : CommandDispatcherBase()
        {
            override val executorFactory: CommandHandlerResolver
                get()
                {
                    return commandHandlerResolver
                }
            
            override val contextFactory: CommandContextFactory = object : CommandContextFactory
            {
                override fun createContext(): CommandContext = object : CommandContext
                {
                    override val notificationEmitter: NotificationEmitter
                        get() = object : NotificationEmitterBase()
                        {
                            override val notificationHandlers: List<NotificationHandler>
                                get() = TODO("Not yet implemented")
                        }
                    
                    override fun <TValue> getValue(name: String, valueClass: Class<TValue>): TValue = TODO("Not yet implemented")
                }
                
            }
            
            override fun getHandler(requestClass: Class<out CommandRequest<*>>): CommandHandler<CommandRequest<*>, *>
            {
                return this.executorFactory.getCommandHandler(requestClass)
            }
        }
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun commandHandlerResolver(
        classScanner: ClassScanner,
        autowireCapableBeanFactory: AutowireCapableBeanFactory,
    ): CommandHandlerResolver = SpringCommandHandlerResolver(
        classScanner = classScanner,
        autowireCapableBeanFactory = autowireCapableBeanFactory,
    )
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun schemaGeneratorConfig(): SchemaGeneratorConfig = SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12)
        // .with(JavaxValidationModule())
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
                .withTitleResolver resolver@{ scope ->
                    return@resolver when (scope)
                    {
                        is MemberScope<*, *> -> scope.getAnnotation(WebInfo::class.java)?.title
                            ?: "T:${scope.name}"
                        
                        else -> scope.type.erasedType.getAnnotation(WebInfo::class.java)?.title
                            ?: "T:${scope.type.typeName}"
                    }
                }
                .withDescriptionResolver resolver@{ scope ->
                    return@resolver when (scope)
                    {
                        is MemberScope<*, *> -> scope.getAnnotation(WebInfo::class.java)?.description
                            ?: "D:${scope.name}"
                        
                        else -> scope.type.erasedType.getAnnotation(WebInfo::class.java)?.description
                            ?: "D:${scope.type.typeName}"
                    }
                }
        }
        .build()
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun schemaGenerator(
        schemaGeneratorConfig: SchemaGeneratorConfig,
    ): SchemaGenerator = SchemaGenerator(schemaGeneratorConfig)
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun commandRequestDescriptorProvider(
        classScanner: ClassScanner,
        schemaGenerator: SchemaGenerator,
    ): CommandRequestDescriptorProvider = DefaultCommandRequestDescriptorProvider(
        classScanner = classScanner,
        schemaGenerator = schemaGenerator,
    )
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
        .disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
        .registerModule(JavaTimeModule())
        .findAndRegisterModules()
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun versionProvider(): ServerVersionProvider
    {
        return DefaultServerVersionProvider()
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun notificationStore(): NotificationStore
    {
        return MemoryNotificationStore()
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun processManager(): ProcessManager
    {
        return DefaultProcessManager(
            executor = Executors.newFixedThreadPool(16),
        )
    }
    
    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    fun processDescriptorProvider(processManager: ProcessManager): ProcessDescriptorProvider
    {
        return DefaultProcessDescriptorProvider(processManager)
    }
    
    // </editor-fold>
    
    override fun setEnvironment(environment: Environment)
    {
        this.environment = environment
    }
    
    override fun registerBeanDefinitions(annotationMetadata: AnnotationMetadata, beanDefinitionRegistry: BeanDefinitionRegistry)
    {
        val attributesMap = annotationMetadata.getAnnotationAttributes(EnableWci::class.java.getCanonicalName())
        val annotationAttributes = AnnotationAttributes(attributesMap!!)
        val componentProvider = ClassPathScanningCandidateComponentProvider(true, this.environment)
        val basePackages = Companion.getBasePackages(annotationMetadata as StandardAnnotationMetadata, annotationAttributes)
            .plus(EnableWci::class.java.packageName)
        
        // componentProvider.addIncludeFilter(AnnotationTypeFilter(MyAnnotation::class.java, true))
        
        for (basePackage in basePackages)
        {
            for (beanDefinition in componentProvider.findCandidateComponents(basePackage))
            {
                val beanClassName: String = Companion.beanNameGenerator.generateBeanName(beanDefinition, beanDefinitionRegistry)
                
                if (!beanDefinitionRegistry.containsBeanDefinition(beanClassName))
                {
                    beanDefinitionRegistry.registerBeanDefinition(beanClassName, beanDefinition)
                }
            }
        }
    }
    
    companion object
    {
        private val beanNameGenerator: BeanNameGenerator = AnnotationBeanNameGenerator.INSTANCE
        
        private fun getBasePackages(metadata: StandardAnnotationMetadata, attributes: AnnotationAttributes): Set<String>
        {
            val basePackages = attributes.getStringArray("basePackages").toSet()
            
            if (basePackages.isEmpty())
            {
                return Collections.singleton(metadata.introspectedClass.packageName)
            }
            
            return basePackages
        }
    }
}
