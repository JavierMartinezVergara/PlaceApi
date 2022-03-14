package com.javiermtz.placescode.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    val location: Location,
    val viewport: Viewport
)
