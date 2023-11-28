package com.luggsoft.wci.core.system

import com.luggsoft.wci.core.util.ifNullOrBlank
import java.util.Properties

class DefaultServerVersionProvider : ServerVersionProvider
{
    private val gitProperties by lazy {
        return@lazy this.javaClass.classLoader.getResourceAsStream(GIT_PROPERTIES_RESOURCE_PATH)
            .use { gitPropertiesInputStream ->
                return@use Properties().also { properties -> properties.load(gitPropertiesInputStream) }
            }
    }
    
    private val tag: String
        get() = this.gitProperties.getProperty(TAG_PROPERTY) ifNullOrBlank DEFAULT_TAG
    
    private val hash: String
        get() = this.gitProperties.getProperty(HASH_PROPERTY) ifNullOrBlank DEFAULT_HASH
    
    override fun getServerVersion(): Version
    {
        return Version.parse("${this.tag}-${this.hash}")
    }
    
    companion object
    {
        const val TAG_PROPERTY = "git.closest.tag.name"
        
        const val HASH_PROPERTY = "git.commit.id"
        
        const val DEFAULT_TAG = "0.0.0"
        const val DEFAULT_HASH = "0000000000000000000000000000000000000000"
        
        const val GIT_PROPERTIES_RESOURCE_PATH = "./git.properties"
    }
}
