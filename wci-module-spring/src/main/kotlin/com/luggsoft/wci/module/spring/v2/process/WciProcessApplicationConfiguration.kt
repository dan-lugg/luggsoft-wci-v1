package com.luggsoft.wci.module.spring.v2.process

import org.springframework.context.EnvironmentAware
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar
import org.springframework.core.env.Environment

@Configuration
@ComponentScan(basePackages = ["com.luggsoft.wci.module.spring.v2.command"])
class WciProcessApplicationConfiguration : ImportBeanDefinitionRegistrar, EnvironmentAware
{
    private lateinit var environment: Environment
    
    override fun setEnvironment(environment: Environment)
    {
        TODO("Not yet implemented")
    }
}
