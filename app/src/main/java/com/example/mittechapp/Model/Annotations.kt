package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Annotations (
	 val source_name : String?=null,
	 val source_description : String?=null,
	 val dataset_name : String?=null,
	 val dataset_link : String?=null,
	 val table_id : String?=null,
	 val topic : String?=null,
	 val subtopic : String?=null
)