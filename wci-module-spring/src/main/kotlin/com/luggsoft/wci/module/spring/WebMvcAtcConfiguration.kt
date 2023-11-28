package com.luggsoft.wci.module.spring

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcAtcConfiguration : WebMvcConfigurer
{
    override fun addResourceHandlers(resourceHandlerRegistry: ResourceHandlerRegistry)
    {
        resourceHandlerRegistry
            .addResourceHandler("/web/**")
            .addResourceLocations("classpath:/web/")
        /*
        .setCachePeriod(3600)
        .resourceChain(true)
        .addResolver(PathResourceResolver())
        */
    }
    
    /*
    internal object IndexFallbackResourceResolver : PathResourceResolver()
    {
        override fun resolveResourceInternal(request: HttpServletRequest?, requestPath: String, locations: MutableList<out Resource>, chain: ResourceResolverChain): Resource?
        {
            println(requestPath);
            
            return super.resolveResourceInternal(request, requestPath, locations, chain)
                ?: super.resolveResourceInternal(request, "$requestPath/index.html", locations, chain)
        }
    }
    */
}
