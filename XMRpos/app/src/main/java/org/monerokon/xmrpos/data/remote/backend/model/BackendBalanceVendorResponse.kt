package org.monerokon.xmrpos.data.remote.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class BackendBalanceVendorResponse(
    val balance: Long,
)