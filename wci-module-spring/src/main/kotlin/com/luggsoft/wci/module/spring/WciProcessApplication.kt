package com.luggsoft.wci.module.spring

import com.luggsoft.wci.module.spring.v2.process.WciProcessApplicationConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(WciProcessApplicationConfiguration::class)
@ComponentScan("com.luggsoft.wci.module.spring.process")
annotation class WciProcessApplication(
    val basePackages: Array<String> = [],
)
