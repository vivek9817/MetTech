package com.example.mittechapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class ItemsData(
    var Item : String?=null,
    var Category : String?=null,
    var Itemprice : String?=null,
)

data class Items(
    var CheckId : String?=null,
    var IsChecked : Boolean = false
)
