package com.moneromerchant.pos.data.remote.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginRequest(
    val vendor_id: Int,
    val name: String,
    val password: String
)
