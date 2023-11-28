package com.luggsoft.wci.core.annotations

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandEnvironments(
    val environments: Array<String>,
)

class MyProcessor : AbstractProcessor()
{
    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean
    {
        for (annotation in annotations)
        {
            for (element in roundEnv.getElementsAnnotatedWith(CommandEnvironments::class.java))
            {
                val commandEnvironments = element.getAnnotation(CommandEnvironments::class.java)
                val environments = commandEnvironments.environments
                println(environments.joinToString())
                
                this.processingEnv.messager.printMessage(javax.tools.Diagnostic.Kind.NOTE, "Hello, World!")
                
                
                
                
            }
        }
        
        return true
    }
}
