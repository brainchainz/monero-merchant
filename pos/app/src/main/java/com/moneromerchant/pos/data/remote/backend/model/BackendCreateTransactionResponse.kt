package com.moneromerchant.pos.data.remote.backend.model

import kotlinx.serialization.Serializable
@Serializable
data class BackendCreateTransactionResponse(
    val id: Int,
    val address: String,
)