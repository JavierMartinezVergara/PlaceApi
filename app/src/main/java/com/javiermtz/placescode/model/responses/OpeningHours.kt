package com.javiermtz.placescode.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class OpeningHours(
    val open_now: Boolean
)
