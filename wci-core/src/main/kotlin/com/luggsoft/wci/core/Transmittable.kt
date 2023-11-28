package com.luggsoft.wci.core

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS
import java.io.Serializable

@JsonTypeInfo(use = CLASS, include = PROPERTY, property = "\$type")
interface Transmittable : Serializable
