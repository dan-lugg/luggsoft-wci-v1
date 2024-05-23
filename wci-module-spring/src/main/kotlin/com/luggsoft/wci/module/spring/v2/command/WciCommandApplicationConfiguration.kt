package com.luggsoft.wci.module.spring.v2.command

import com.luggsoft.wci.module.spring.WciCommandApplication
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar
import org.springframework.core.annotation.AnnotationAttributes
import org.springframework.core.env.Environment
import org.springframework.core.type.AnnotationMetadata

@Configuration
@ComponentScan(basePackages = ["com.luggsoft.wci.module.spring.v2.command"])
class WciCommandApplicationConfiguration : ImportBeanDefinitionRegistrar, EnvironmentAware
{
    private lateinit var environment: Environment
    
    override fun setEnvironment(environment: Environment)
    {
        this.environment = environment
    }
    
    override fun registerBeanDefinitions(metadata: AnnotationMetadata, registry: BeanDefinitionRegistry)
    {
        // Create a component provider for the provided environment.
        val attributesMap = metadata.getAnnotationAttributes(WciCommandApplication::class.java.canonicalName)
            ?: throw IllegalArgumentException("Annotation not found")
        val annotationAttributes = AnnotationAttributes(attributesMap)
        val componentProvider = ClassPathScanningCandidateComponentProvider(true, this.environment)
        
        
    }
}
