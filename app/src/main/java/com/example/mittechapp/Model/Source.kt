package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val measures: List<String>? = null,
    val annotations: Annotations? = null,
    val name: String? = null,
    val substitutions: List<String>? = null
)