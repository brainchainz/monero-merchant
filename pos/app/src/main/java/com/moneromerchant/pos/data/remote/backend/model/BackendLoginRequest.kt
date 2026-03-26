package com.moneromerchant.pos.data.remote.backend.model

data class BackendLoginRequest(
    val vendor_id: Int,
    val name: String,
    val password: String,
)