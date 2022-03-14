package com.javiermtz.placescode.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class PlusCode(
    val compound_code: String,
    val global_code: String
)
