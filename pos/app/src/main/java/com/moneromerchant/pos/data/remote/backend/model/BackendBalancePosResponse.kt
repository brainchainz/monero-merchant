package com.moneromerchant.pos.data.remote.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class BackendBalancePosResponse(
    val balance: Long,
)