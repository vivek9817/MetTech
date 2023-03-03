package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class PopulationResponse(
	val data : List<Data>?=null,
	val source : List<Source>?=null
)