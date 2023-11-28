package com.luggsoft.wci.module.spring

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Import(EnableAtcConfiguration::class)
@ComponentScan("com.luggsoft.wci.module.spring")
annotation class EnableWci(
    val basePackages: Array<String> = [],
)
