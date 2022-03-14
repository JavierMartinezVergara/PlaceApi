package com.javiermtz.placescode.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResponsePlaces(
    val html_attributions: List<String> = listOf(),
    val next_page_token: String,
    val results: List<Result>,
    val status: String
)
