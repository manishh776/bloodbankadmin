package com.bloodbank.admin.data.network.entities

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Admin(
    var username: String? = "",
    var password: String? = "",
    var token: String? = ""
)
