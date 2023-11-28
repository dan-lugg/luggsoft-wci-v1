package com.luggsoft.wci.core.system

import com.luggsoft.wci.core.Transmittable
import com.luggsoft.wci.core.util.EMPTY_STRING
import org.intellij.lang.annotations.Language
import java.util.regex.Pattern

/**
 * Represents a semantic version, following the pattern:
 *
 * ```
 * [ MAJOR [ "." MINOR  [ "." PATCH ] ] ] [ "-" BUILD ]
 * ```
 *
 * Such as:
 *
 * - 1.2.3-912ec803b2ce49e4a541068d495ab570
 * - 1.2.3
 * - 1.2
 *
 * @see Transmittable
 */
data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val build: String? = null,
) : Comparable<Version>, Transmittable
{
    override fun compareTo(other: Version): Int
    {
        if (this.major == other.major)
        {
            if (this.minor == other.minor)
            {
                if (this.patch == other.patch)
                {
                    if (this.build == other.build)
                    {
                        return 0
                    }
                    
                    return this.build?.compareTo(other.build ?: EMPTY_STRING) ?: 0
                }
                
                return this.patch.compareTo(other.patch)
            }
            
            return this.minor.compareTo(other.minor)
        }
        
        return this.major.compareTo(other.major)
    }
    
    override fun toString(): String
    {
        return when (this.build)
        {
            null -> "${this.major}.${this.minor}.${this.patch}"
            else -> "${this.major}.${this.minor}.${this.patch}-${this.build}"
        }
    }
    
    companion object
    {
        private const val MAJOR = "MAJOR"
        private const val MINOR = "MINOR"
        private const val PATCH = "PATCH"
        private const val BUILD = "BUILD"
        
        @Language("RegExp")
        private const val VERSION_EXPRESSION = "^(?<$MAJOR>[0-9]|[1-9][0-9]+)(?:\\.(?<$MINOR>[0-9]|[1-9][0-9]+)(?:\\.(?<$PATCH>[0-9]|[1-9][0-9]+))?)?(?:-(?<$BUILD>[a-zA-Z0-9-]+))?\$"
        
        private val versionPattern by lazy { Pattern.compile(VERSION_EXPRESSION) }
        
        fun parse(input: String): Version
        {
            val matcher = versionPattern.matcher(input)
            
            if (matcher.matches())
            {
                
                return Version(
                    major = matcher.group(MAJOR)?.toInt() ?: 0,
                    minor = matcher.group(MINOR)?.toInt() ?: 0,
                    patch = matcher.group(PATCH)?.toInt() ?: 0,
                    build = matcher.group(BUILD),
                )
            }
            
            throw IllegalArgumentException("Failed to parse $input")
        }
    }
}
