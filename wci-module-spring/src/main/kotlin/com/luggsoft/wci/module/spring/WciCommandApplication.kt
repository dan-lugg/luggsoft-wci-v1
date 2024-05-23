package com.luggsoft.wci.module.spring

import com.luggsoft.wci.module.spring.v2.command.WciCommandApplicationConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(WciCommandApplicationConfiguration::class)
@ComponentScan("com.luggsoft.wci.module.spring.command")
annotation class WciCommandApplication(
    val basePackages: Array<String> = [],
)
