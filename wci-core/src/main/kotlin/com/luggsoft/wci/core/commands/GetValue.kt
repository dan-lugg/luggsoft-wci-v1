package com.luggsoft.wci.core.commands

inline fun <reified TValue> CommandContext.getValue(name: String): TValue? = this.getValue(name, TValue::class.java)
