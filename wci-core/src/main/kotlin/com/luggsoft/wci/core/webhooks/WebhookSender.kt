package com.luggsoft.wci.core.webhooks

import com.luggsoft.wci.core.Transmittable

interface WebhookSender
{
    fun <TPayload : Transmittable> dispatchWebhook(destination: WebhookDestination, payload: TPayload)
}
