package com.luggsoft.wci.core.webhooks

data class WebhookDestination(
    val url: String,
    val method: WebhookMethod,
)
