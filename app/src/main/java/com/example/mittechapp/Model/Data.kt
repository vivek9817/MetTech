package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
//    val `ID Nation` : String? = null,
    val Nation: String? = null,
//    val `ID Year` : Int? = 0,
    val Year: String? = null,
    val Population: Long? = 0,
//    val `Slug Nation` : String? = null
)