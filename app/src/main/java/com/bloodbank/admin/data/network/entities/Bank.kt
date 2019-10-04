package com.bloodbank.admin.data.network.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Bank(
    var id: String? = "",
    var name: String? = "",
    var mobile: String? = "",
    var token: String? = "",
    var imageUrl:String? = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var username: String? = "",
    var password: String? = ""
)
