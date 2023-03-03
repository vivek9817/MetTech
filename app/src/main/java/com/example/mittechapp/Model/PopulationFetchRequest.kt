package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class PopulationFetchRequest(
    var store_id : String?="",
    var u_id : String?="",
    var access_type : String?="",
    var source : String?=""
)
